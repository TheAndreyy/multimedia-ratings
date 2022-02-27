package com.example.ratingsapi.multimedia

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/multimedia")
class MultimediaController(val multimediaService: MultimediaService) {

    @PostMapping
    fun addMultimedia(@RequestBody multimediaDto: MultimediaDto): ResponseEntity<String> {
        multimediaService.addMultimedia(multimediaDto)
        return ResponseEntity.accepted().build()
    }

    @GetMapping
    fun getMultimedia(@RequestParam title: String) = ResponseEntity.ok().body(
        multimediaService.getMultimediaByTitle(title)
    )
}