package com.example.ratingsapi.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.config.TopicBuilder

@Configuration
class KafkaTopicConfig {

    companion object {
        const val TEST_TOPIC = "Test3"
        const val COMMENT_TOPIC = "COMMENTS"
    }

    @Bean
    fun testTopic() = TopicBuilder
        .name(TEST_TOPIC)
        .build()

    @Bean
    fun commentTopic() = TopicBuilder
        .name(COMMENT_TOPIC)
        .build()

}