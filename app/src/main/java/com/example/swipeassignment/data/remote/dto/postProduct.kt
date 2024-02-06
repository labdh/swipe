package com.example.swipeassignment.data.remote.dto

import com.example.swipeassignment.domain.model.Product

data class postProduct(val message:String,
                       val product_details: Product,
                       val product_id:Int, val sucess:Boolean)