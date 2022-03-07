package com.example.ratingsapi.comment

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/comments")
class CommentController(val commentRepository: CommentRepository) {

    @PostMapping
    fun addComment(@RequestBody comment: Comment): ResponseEntity<Void> {
        commentRepository.save(comment)
        return ResponseEntity.accepted().build()
    }

    @GetMapping
    fun getComments(): ResponseEntity<Iterable<Comment>> {
        val comments = commentRepository.findAll()
        return ResponseEntity.ok().body(comments)
    }

}