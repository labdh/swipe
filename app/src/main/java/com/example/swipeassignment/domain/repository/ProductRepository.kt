package com.example.swipeassignment.domain.repository

import android.telecom.Call
import com.example.swipeassignment.data.remote.dto.ProductDto
import com.example.swipeassignment.data.remote.dto.postProduct
import com.example.swipeassignment.domain.model.Product
import retrofit2.Response

interface ProductRepository {

    suspend fun getproduct() : List<ProductDto>
    suspend fun addproduct(data:Product) : Response<postProduct>
}