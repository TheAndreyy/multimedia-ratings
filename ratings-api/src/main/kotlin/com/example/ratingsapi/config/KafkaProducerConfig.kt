package com.example.ratingsapi.config

import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory
import org.springframework.kafka.support.serializer.JsonSerializer

@Configuration
class KafkaProducerConfig(
    @Value("\${spring.kafka.bootstrap-servers}")
    private val bootstrapServers: String
) {

    fun producerConfig(): MutableMap<String, Any> {
        val config: MutableMap<String, Any> = HashMap()

        config[ProducerConfig.BOOTSTRAP_SERVERS_CONFIG] = bootstrapServers
        config[ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG] = StringSerializer::class.java
        config[ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG] = JsonSerializer::class.java
        config[JsonSerializer.TYPE_MAPPINGS] = "multimedia:com.example.ratingsapi.multimedia.MultimediaDto"
        config[JsonSerializer.TYPE_MAPPINGS] = "comment:com.example.ratingsapi.comment.CommentDto"
        return config
    }

    @Bean
    fun producerFactory(): ProducerFactory<String, Any> = DefaultKafkaProducerFactory(producerConfig())

    @Bean
    fun kafkaTemplate(producerFactory: ProducerFactory<String, Any>) = KafkaTemplate(producerFactory)

}