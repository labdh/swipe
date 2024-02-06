package com.example.swipeassignment.presentation.product_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.swipeassignment.R
import com.example.swipeassignment.databinding.ProductItemBinding
import com.example.swipeassignment.domain.model.Product

class ProductAdapter(var data: ArrayList<Product>) :
    RecyclerView.Adapter<ProductAdapter.productViewholder>() {

    fun setSearchList(dataSearchList: ArrayList<Product>) {
        data = dataSearchList
        notifyDataSetChanged()
    }

    class productViewholder(val productitemBinding: ProductItemBinding) : RecyclerView.ViewHolder(productitemBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)= productViewholder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.product_item,
            parent,false
        )
    )


    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: productViewholder, position: Int) {
        val product = data[position]
        if(product.price.length > 3) product.price = product.price.subSequence(0,3).toString()
        product.product_name = product.product_name.trim()
        product.product_type = product.product_type.trim()
        if(product.tax.length > 2) product.tax = product.tax.subSequence(0,2).toString()
        holder.productitemBinding.product = product
    }
}