package br.com.gtb.libraries.data.model

import br.com.gtb.libraries.data.entity.ProductEntity

data class Product(
    val imageURL: String,
    val name: String,
    val description: String,
)

fun Product.asEntity(): ProductEntity {
    return ProductEntity(
        imageURL = imageURL,
        name = name,
        description = description,
    )
}

fun List<Product>.asEntity(): List<ProductEntity> {
    return map {
        it.asEntity()
    }
}