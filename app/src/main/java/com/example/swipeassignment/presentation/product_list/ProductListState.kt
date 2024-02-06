package com.example.swipeassignment.presentation.product_list

import com.example.swipeassignment.domain.model.Product


data class ProductListState(
    var isLoading: Boolean = false,
    var products: List<Product> = emptyList(),
    val error: String = ""
)
