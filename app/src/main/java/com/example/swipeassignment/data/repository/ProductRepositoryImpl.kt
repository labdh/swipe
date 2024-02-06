package com.example.swipeassignment.data.repository

import com.example.swipeassignment.data.remote.SwipeAPI
import com.example.swipeassignment.data.remote.dto.ProductDto
import com.example.swipeassignment.data.remote.dto.postProduct
import com.example.swipeassignment.domain.model.Product
import com.example.swipeassignment.domain.repository.ProductRepository
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val api : SwipeAPI
) : ProductRepository {

    override suspend fun getproduct(): List<ProductDto> {
        return api.getproduct()
    }

    override suspend fun addproduct(data: Product): Response<postProduct> {
        return api.addproduct(data)
    }
}