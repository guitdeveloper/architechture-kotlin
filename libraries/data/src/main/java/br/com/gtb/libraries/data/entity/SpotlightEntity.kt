package br.com.gtb.libraries.data.entity

import br.com.gtb.libraries.data.model.Spotlight
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "spotlights")
class SpotlightEntity constructor(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val description: String,
    @ColumnInfo(name = "banner_url")
    val bannerURL: String,
)

fun SpotlightEntity.asDomainModel(): Spotlight {
    return Spotlight(
        bannerURL = bannerURL,
        name = name,
        description = description,
    )
}

fun List<SpotlightEntity>.asDomainModel(): List<Spotlight> {
    return map {
        it.asDomainModel()
    }
}