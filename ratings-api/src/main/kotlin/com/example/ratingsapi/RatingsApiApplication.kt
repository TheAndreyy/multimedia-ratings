package com.example.ratingsapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@SpringBootApplication
class RatingsApiApplication {

    @GetMapping("/")
    fun hello() = ResponseEntity
            .ok()
            .body("Hello from API!")
}

fun main(args: Array<String>) {
    runApplication<RatingsApiApplication>(*args)
}
