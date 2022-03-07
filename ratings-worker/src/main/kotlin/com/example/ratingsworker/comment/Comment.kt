package com.example.ratingsworker.comment

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.Instant

@Document(collection = "comments")
class Comment(
    @Id
    var id: String?,
    var userName: String,
    var creationDate: Instant,
    var body: String,
    var rating: Int
) {

    constructor(commentDto: CommentDto) : this(
        null,
        commentDto.userName,
        commentDto.creationDate.toInstant(),
        commentDto.body,
        commentDto.rating
    )
}