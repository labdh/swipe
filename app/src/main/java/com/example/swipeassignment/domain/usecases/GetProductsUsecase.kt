package com.example.swipeassignment.domain.usecases

import android.util.Log
import androidx.lifecycle.liveData
import com.example.swipeassignment.common.Resource
import com.example.swipeassignment.data.remote.dto.toproduct
import com.example.swipeassignment.domain.model.Product
import com.example.swipeassignment.domain.repository.ProductRepository
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


class GetProductsUsecase @Inject constructor(
    private val repository : ProductRepository
) {
    operator fun invoke() = liveData {
        try {
            val products = repository.getproduct().map { it.toproduct() }
            emit(Resource.Success<List<Product>>(products))
        }catch (e: IOException){
            emit(Resource.Error<List<Product>>("Oops! Please check Internet connection"))
        }catch (e: HttpException){
            emit(Resource.Error<List<Product>>(e.localizedMessage ?: "An unexpected Error"))
        }
    }
}