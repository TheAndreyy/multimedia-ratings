package com.example.ratingsworker.config

import com.example.ratingsworker.multimedia.MultimediaDto
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.kafka.support.serializer.JsonDeserializer

@EnableKafka
@Configuration
class KafkaConsumerConfig(
    @Value("\${spring.kafka.bootstrap-servers}")
    private val bootstrapServers: String
) {

    fun consumerConfig(): MutableMap<String, Any> {
        val config: MutableMap<String, Any> = HashMap()

        config[ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG] = bootstrapServers
        config[ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java
        config[ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG] = JsonDeserializer::class.java
        config[JsonDeserializer.TYPE_MAPPINGS] = "multimedia:com.example.ratingsworker.multimedia.MultimediaDto"
        return config
    }

    @Bean
    fun consumerFactory(): ConsumerFactory<String, MultimediaDto> =
        DefaultKafkaConsumerFactory(consumerConfig(), StringDeserializer(), JsonDeserializer(MultimediaDto::class.java))

    @Bean
    fun kafkaListenerFactory(consumerFactory: ConsumerFactory<String, MultimediaDto>):
            ConcurrentKafkaListenerContainerFactory<String, MultimediaDto> {
        val factory: ConcurrentKafkaListenerContainerFactory<String, MultimediaDto> =
            ConcurrentKafkaListenerContainerFactory()
        factory.consumerFactory = consumerFactory
        return factory
    }

}
