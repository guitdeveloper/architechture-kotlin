package br.com.gtb.libraries.data.response

import br.com.gtb.libraries.data.model.Cash
import br.com.gtb.libraries.data.model.Product
import br.com.gtb.libraries.data.model.Spotlight
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ArchitectureProductResponse(
    val cash: Cash,
    val products: List<Product>,
    @SerializedName("spotlight")
    val spotlights: List<Spotlight>
) : Serializable
