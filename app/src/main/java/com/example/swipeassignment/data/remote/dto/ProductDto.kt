package com.example.swipeassignment.data.remote.dto

import com.example.swipeassignment.domain.model.Product

data class ProductDto(
    val image: String,
    val price: Double,
    val product_name: String,
    val product_type: String,
    val tax: Double
)
fun ProductDto.toproduct(): Product{
    return Product(image,price.toInt().toString(),product_name,product_type,tax.toInt().toString())
}