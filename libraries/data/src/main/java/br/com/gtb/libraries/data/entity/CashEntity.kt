package br.com.gtb.libraries.data.entity

import br.com.gtb.libraries.data.model.Cash
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cash")
class CashEntity constructor(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val description: String,
    @ColumnInfo(name = "image_url")
    val imageURL: String,
)

fun CashEntity.asDomainModel(): Cash {
    return Cash(
        bannerURL = imageURL,
        title = name,
        description = description,
    )
}
