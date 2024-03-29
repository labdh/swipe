package com.example.swipeassignment.di

import com.example.swipeassignment.common.Constants
import com.example.swipeassignment.data.remote.SwipeAPI
import com.example.swipeassignment.data.repository.ProductRepositoryImpl
import com.example.swipeassignment.domain.repository.ProductRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideSwipeApi() : SwipeAPI{
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SwipeAPI::class.java)

    }

    @Provides
    @Singleton
    fun provideProductRepository(api : SwipeAPI):ProductRepository{
        return ProductRepositoryImpl(api)
    }
}