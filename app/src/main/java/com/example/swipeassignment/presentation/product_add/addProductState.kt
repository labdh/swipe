package com.example.swipeassignment.presentation.product_add

import com.example.swipeassignment.domain.model.Product

data class addProductState(
    var isLoading: Boolean = false,
    var products: Product? = null,
    val error: String = ""
)
