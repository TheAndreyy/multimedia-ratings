package com.example.ratingsapi.multimedia

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import java.time.LocalDate

@Document
data class Multimedia(
    @Id
    var id: String?,
    @Indexed
    var title: String,
    var director: String,
    var releaseDate: LocalDate
) {
    constructor(multimediaDto: MultimediaDto) : this(
        null,
        multimediaDto.title,
        multimediaDto.director,
        LocalDate.parse(multimediaDto.releaseDate)
    )
}

data class MultimediaDto(
    val title: String,
    val director: String,
    val releaseDate: String
)

@Repository
interface MultimediaRepository: MongoRepository<Multimedia, String> {
    fun findAllByTitle(title: String): List<Multimedia>
}
