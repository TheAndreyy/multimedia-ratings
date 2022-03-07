package com.example.ratingsworker

import com.example.ratingsworker.multimedia.MultimediaDto
import mu.KotlinLogging
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

private val logger = KotlinLogging.logger {}
private const val TEST_TOPIC = "Test3"

@Component
class KafkaListeners {

    @KafkaListener(
        topics = [TEST_TOPIC],
        groupId = "default",
        containerFactory = "kafkaListenerFactory"
    )
    fun listener(multimedia: MultimediaDto) {
        logger.info { "Listener received: $multimedia" }
    }
}
