package br.com.gtb.libraries.data.service

import br.com.gtb.libraries.data.response.ArchitectureProductResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface ArchitectureService {
    @Headers("Content-Type: application/json")
    @GET("sandbox/products")
    fun getProducts(): Call<ArchitectureProductResponse>
}