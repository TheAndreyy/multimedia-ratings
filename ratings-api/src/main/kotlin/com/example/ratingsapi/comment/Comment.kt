package com.example.ratingsapi.comment

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import java.time.ZonedDateTime

@RedisHash("Comments")
data class Comment(
    val userName: String,
    val creationDate: ZonedDateTime,
    val comment: String,
) {
    @get:Id
    var id: String? = null
}
