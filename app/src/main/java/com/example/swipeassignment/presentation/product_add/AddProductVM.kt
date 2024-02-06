package com.example.swipeassignment.presentation.product_add

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.swipeassignment.common.Resource
import com.example.swipeassignment.domain.model.Product
import com.example.swipeassignment.domain.repository.ProductRepository
import com.example.swipeassignment.domain.usecases.addProductUsecase
import com.example.swipeassignment.presentation.product_list.ProductListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddProductVM @Inject constructor(private val addProductUsecase: addProductUsecase) : ViewModel() {

//    val productsLiveData : LiveData<Product> = repository.addproduct()
//
//    fun getDrinkbyId(data : Product) {
//        viewModelScope.launch {
//            repository.addproduct(data)
//        }
//    }


    private val _state = MutableLiveData(addProductState())
    val state: LiveData<addProductState> get() = _state

    fun addProduct(data:Product){

        addProductUsecase(data).observeForever {
            when (it) {
                is Resource.Error -> {
                    _state.value = addProductState(error = it.message ?: "An unexpected error occured")
                    Log.e("checkpost", "checking error in VM : ${it.message}")
                }

                is Resource.Success -> {
                    _state.value = addProductState(products = it.data)
                    Log.e("checkpost", "checking data in VM : ${it.data}")
                }

                is Resource.Loading -> {
                    _state.value = addProductState(isLoading = true)
                }
            }
        }
    }
}