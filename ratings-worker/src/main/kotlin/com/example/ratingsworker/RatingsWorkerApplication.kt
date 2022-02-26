package com.example.ratingsworker

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@SpringBootApplication
class RatingsWorkerApplication {

    @GetMapping("/")
    fun hello() = ResponseEntity
        .ok()
        .body("Hello from Worker!")
}

fun main(args: Array<String>) {
    runApplication<RatingsWorkerApplication>(*args)
}
