package com.example.ratingsapi.multimedia

import org.springframework.stereotype.Service

@Service
class MultimediaService(val multimediaRepository: MultimediaRepository) {

    fun addMultimedia(multimediaDto: MultimediaDto) {
        val multimedia = Multimedia(multimediaDto)
        multimediaRepository.save(multimedia)
    }

    fun getMultimediaByTitle(title: String) = multimediaRepository.findAllByTitle(title)

}
