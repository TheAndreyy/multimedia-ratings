package com.example.ratingsapi.multimedia

import mu.KotlinLogging
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

private val logger = KotlinLogging.logger {}

@Service
class MultimediaService(val multimediaRepository: MultimediaRepository, val kafkaTemplate: KafkaTemplate<String, MultimediaDto>) {

    fun addMultimedia(multimediaDto: MultimediaDto) {
        val multimedia = Multimedia(multimediaDto)
        multimediaRepository.save(multimedia)
    }

    fun getMultimediaByTitle(title: String) = multimediaRepository.findAllByTitle(title)

    fun sendMultimedia(multimediaDto: MultimediaDto) {
        kafkaTemplate.send("Test", multimediaDto)
        logger.info {"Test send succesful"}
    }

}
