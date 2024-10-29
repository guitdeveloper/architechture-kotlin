package br.com.gtb.libraries.data

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private val gson: Gson = GsonBuilder()
    .create()

private val client: OkHttpClient.Builder = OkHttpClient.Builder()

fun getRetrofit(): Retrofit {
    return Retrofit.Builder()
        .baseUrl("https://7hgi9vtkdc.execute-api.sa-east-1.amazonaws.com/")
        .client(client.build())
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

}
