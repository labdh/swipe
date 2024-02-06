package com.example.swipeassignment.domain.usecases

import android.util.Log
import androidx.lifecycle.liveData
import com.example.swipeassignment.common.Resource
import com.example.swipeassignment.data.repository.ProductRepositoryImpl
import com.example.swipeassignment.domain.model.Product
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


class addProductUsecase @Inject constructor(
    private val repository : ProductRepositoryImpl
) {
    operator fun invoke(data:Product) = liveData {
        try {
            val products = repository.addproduct(data).body()
            Log.e("checkpost", "checking data in adduse-cases: ${products}")
            Log.e("checkpost", "checking data in adduse-cases: ${data}")
            if (products != null) {
                emit(Resource.Success<Product>(products.product_details))
            }
        }catch (e: IOException){
            Log.e("checkpost", "checking ioex in addusecases: ${e}")
            emit(Resource.Error<Product>("Oops! Please check Internet connection"))
        }catch (e: HttpException){
            Log.e("checkpost", "checking http in addusecases: ${e}")
            emit(Resource.Error<Product>(e.localizedMessage ?: "An unexpected Error"))
        }
    }
}