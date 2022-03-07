package com.example.ratingsworker.comment

import mu.KotlinLogging
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service
import java.time.ZonedDateTime

private val logger = KotlinLogging.logger {}
private const val COMMENT_TOPIC = "COMMENTS"

@Service
class CommentService(val commentRepository: CommentRepository) {

    @KafkaListener(
        topics = [COMMENT_TOPIC],
        groupId = "default",
        containerFactory = "commentKafkaListenerFactory"
    )
    fun addComment(commentDto: CommentDto) {
        logger.info { "Listener received: $commentDto" }
        val comment = Comment(commentDto)
        commentRepository.save(comment)
    }
}

data class CommentDto(
    var userName: String,
    var creationDate: ZonedDateTime,
    var body: String,
    var rating: Int
)
