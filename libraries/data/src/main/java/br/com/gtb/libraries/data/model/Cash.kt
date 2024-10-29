package br.com.gtb.libraries.data.model

import br.com.gtb.libraries.data.entity.CashEntity

data class Cash(
    val bannerURL: String,
    val title: String,
    val description: String,
)

fun Cash.asEntity(): CashEntity {
    return CashEntity(
        imageURL = bannerURL,
        name = title,
        description = description,
    )
}