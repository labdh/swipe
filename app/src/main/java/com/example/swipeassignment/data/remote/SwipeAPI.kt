package com.example.swipeassignment.data.remote

import com.example.swipeassignment.data.remote.dto.ProductDto
import com.example.swipeassignment.data.remote.dto.postProduct
import com.example.swipeassignment.domain.model.Product
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface SwipeAPI {

    @GET("/api/public/get")
    suspend fun getproduct() : List<ProductDto>

    @POST("/api/public/add")
    suspend fun addproduct(@Body params: Product) : Response<postProduct>
}