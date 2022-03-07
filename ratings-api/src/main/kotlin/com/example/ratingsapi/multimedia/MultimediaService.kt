package com.example.ratingsapi.multimedia

import com.example.ratingsapi.config.KafkaTopicConfig
import mu.KotlinLogging
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

private val logger = KotlinLogging.logger {}

@Service
class MultimediaService(
    val multimediaRepository: MultimediaRepository,
    val kafkaTemplate: KafkaTemplate<String, MultimediaDto>
) {

    fun addMultimedia(multimediaDto: MultimediaDto) {
        val multimedia = Multimedia(multimediaDto)
        multimediaRepository.save(multimedia)
    }

    fun getMultimediaByTitle(title: String) = multimediaRepository.findAllByTitle(title)

    fun sendMultimedia(multimediaDto: MultimediaDto) {
        kafkaTemplate.send(KafkaTopicConfig.TEST_TOPIC, multimediaDto)
        logger.info { "Test send succesful" }
    }

}

data class MultimediaDto(
    val title: String,
    val director: String,
    val releaseDate: String
)
