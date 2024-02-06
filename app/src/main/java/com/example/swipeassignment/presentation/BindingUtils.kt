package com.example.swipeassignment.presentation

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.swipeassignment.R


@BindingAdapter("image")
fun loadImage(view: ImageView, url:String?){
    if(url.isNullOrEmpty()){
        view.setImageResource(R.drawable.swipesplash1)
    }
    else Glide.with(view).load(url).into(view)
}