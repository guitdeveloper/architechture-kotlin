package br.com.gtb.libraries.data.model

import br.com.gtb.libraries.data.entity.SpotlightEntity

data class Spotlight(
    val bannerURL: String,
    val name: String,
    val description: String,
)

fun Spotlight.asEntity(): SpotlightEntity {
    return SpotlightEntity(
        bannerURL = bannerURL,
        name = name,
        description = description,
    )
}

fun List<Spotlight>.asEntity(): List<SpotlightEntity> {
    return map {
        it.asEntity()
    }
}