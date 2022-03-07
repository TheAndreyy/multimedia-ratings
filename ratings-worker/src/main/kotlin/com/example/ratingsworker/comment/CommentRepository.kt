package com.example.ratingsworker.comment

import org.springframework.data.mongodb.repository.MongoRepository

interface CommentRepository : MongoRepository<Comment, String> {
}
