package com.example.swipeassignment.presentation.product_list

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.swipeassignment.common.Resource
import com.example.swipeassignment.domain.usecases.GetProductsUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class ProductlistVM @Inject constructor(
    private val getProductsUseCase: GetProductsUsecase
): ViewModel() {

    init{
        getProduct()
    }

    private val _state = MutableLiveData(ProductListState())
    val state: LiveData<ProductListState> get() = _state

    fun getProduct(){
        getProductsUseCase().observeForever {
            when (it) {
                is Resource.Error -> {
                    _state.value =ProductListState(error = it.message ?: "An unexpected error occured")
                }
                is Resource.Success -> {
                    _state.value = ProductListState(products = it.data ?: emptyList())
                }
                is Resource.Loading -> {
                    _state.value = ProductListState(isLoading = true)
                }
            }
        }
    }

}