package br.com.gtb.libraries.data.entity

import br.com.gtb.libraries.data.model.Product
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
class ProductEntity constructor(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val description: String,
    @ColumnInfo(name = "image_url")
    val imageURL: String,
)

fun ProductEntity.asDomainModel(): Product {
    return Product(
        imageURL = imageURL,
        name = name,
        description = description,
    )
}

fun List<ProductEntity>.asDomainModel(): List<Product> {
    return map {
        it.asDomainModel()
    }
}