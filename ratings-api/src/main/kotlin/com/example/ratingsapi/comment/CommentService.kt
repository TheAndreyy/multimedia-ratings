package com.example.ratingsapi.comment

import com.example.ratingsapi.config.KafkaTopicConfig
import mu.KotlinLogging
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service
import java.time.ZonedDateTime

private val logger = KotlinLogging.logger {}

@Service
class CommentService(
    val kafkaTemplate: KafkaTemplate<String, Any>,
    val commentRepository: CommentRepository
) {

    fun addComment(commentDto: CommentDto) {
        kafkaTemplate.send(KafkaTopicConfig.COMMENT_TOPIC, commentDto)
        logger.info { "Comment send successfully" }

        val comment = Comment(commentDto)
        commentRepository.save(comment)
    }

    fun getComments(): Iterable<Comment> = commentRepository.findAll()

}

data class CommentDto(
    var userName: String,
    var creationDate: ZonedDateTime,
    var body: String,
    var rating: Int
)
