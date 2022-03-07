package com.example.ratingsapi.multimedia

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
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
