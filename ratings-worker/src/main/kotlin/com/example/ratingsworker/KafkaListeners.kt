package com.example.ratingsworker

import com.example.ratingsworker.multimedia.MultimediaDto
import mu.KotlinLogging
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

private val logger = KotlinLogging.logger {}

@Component
class KafkaListeners {

    @KafkaListener(
        topics = ["Test3"],
        groupId = "default",
        containerFactory = "kafkaListenerFactory"
    )
    fun listener(multimedia: MultimediaDto) {
        logger.info { "Listener received: $multimedia" }
    }
}