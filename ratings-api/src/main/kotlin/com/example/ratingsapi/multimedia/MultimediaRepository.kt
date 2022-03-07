package com.example.ratingsapi.multimedia

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface MultimediaRepository : MongoRepository<Multimedia, String> {

    fun findAllByTitle(title: String): List<Multimedia>
}
