package com.example.sample.application

import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.http.GET

interface SampleRetrofitService {

    @GET("/api/sample/test")
    fun test(): Call<String>

    @GET("/rest-api/products")
    fun mariadbProducts(): Call<Result>

    data class Result(
        @SerializedName("products_list")
        val productsList: List<Product>,
    ) {
        data class Product(
            @SerializedName("product_id")
            val productId: String?,

            @SerializedName("name")
            val name: String?,

            @SerializedName("description")
            val description: String?,

            @SerializedName("license")
            val license: String?,
        )
    }
}