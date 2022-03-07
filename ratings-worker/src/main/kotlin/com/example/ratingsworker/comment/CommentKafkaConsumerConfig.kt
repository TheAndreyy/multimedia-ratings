package com.example.ratingsworker.comment

import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.kafka.support.serializer.JsonDeserializer

@Configuration
class CommentKafkaConsumerConfig(
    @Value("\${spring.kafka.bootstrap-servers}")
    private val bootstrapServers: String
) {

    private fun consumerConfig(): MutableMap<String, Any> {
        val config: MutableMap<String, Any> = HashMap()

        config[ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG] = bootstrapServers
        config[ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java
        config[ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG] = JsonDeserializer::class.java
        config[JsonDeserializer.TYPE_MAPPINGS] = "comment:com.example.ratingsworker.comment.CommentDto"
        return config
    }

    @Bean
    fun commentConsumerFactory(): ConsumerFactory<String, CommentDto> =
        DefaultKafkaConsumerFactory(consumerConfig(), StringDeserializer(), JsonDeserializer(CommentDto::class.java))

    @Bean
    fun commentKafkaListenerFactory(consumerFactory: ConsumerFactory<String, CommentDto>):
            ConcurrentKafkaListenerContainerFactory<String, CommentDto> {
        val factory: ConcurrentKafkaListenerContainerFactory<String, CommentDto> =
            ConcurrentKafkaListenerContainerFactory()
        factory.consumerFactory = consumerFactory
        return factory
    }

}
