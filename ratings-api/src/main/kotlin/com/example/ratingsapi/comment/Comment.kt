package com.example.ratingsapi.comment

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import java.time.ZonedDateTime

@RedisHash("Comments")
data class Comment(
    @Id
    var id: String?,
    var userName: String,
    var creationDate: ZonedDateTime,
    var body: String,
    var rating: Int
) {

    constructor(commentDto: CommentDto) : this(
        null,
        commentDto.userName,
        commentDto.creationDate,
        commentDto.body,
        commentDto.rating
    )
}
