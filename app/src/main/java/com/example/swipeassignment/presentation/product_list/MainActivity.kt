package com.example.swipeassignment.presentation.product_list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.swipeassignment.R
import com.example.swipeassignment.databinding.ActivityMainBinding
import com.example.swipeassignment.domain.model.Product
import com.example.swipeassignment.presentation.NetworkManager
import com.example.swipeassignment.presentation.product_add.AddProductFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var addProductFragment: AddProductFragment
    lateinit var mainViewModel: ProductlistVM
    lateinit var adapter: ProductAdapter
    lateinit var data : ArrayList<Product>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        mainViewModel = ViewModelProvider(this).get(ProductlistVM::class.java)
        addProductFragment = AddProductFragment()
        setContentView(binding.root)
        checkInternet()
        setProducts()
        binding.search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    searchList(newText)
                }
                return false
            }
        })
        binding.addProduct.setOnClickListener {
            AddProductFragment().show(supportFragmentManager,"AddNewProduct")
        }

    }

    private fun checkInternet() {
        val networkManager = NetworkManager(this)
        networkManager.observe(this){
            val inflate = findViewById<View>(R.id.networklayout)
            if(it){
                inflate.visibility=View.GONE
                binding.addProduct.visibility = View.VISIBLE
            }
            else{
                inflate.visibility=View.VISIBLE
                binding.addProduct.visibility = View.GONE
            }
        }
    }

    private fun setProducts(){
        mainViewModel.state.observe(this, Observer {
            data =  ArrayList(it.products)
            adapter = ProductAdapter(data)
            binding.productList.adapter = adapter
            binding.productList.layoutManager = GridLayoutManager(this,2, RecyclerView.VERTICAL,false)
        })
    }

    private fun searchList(text: String){
        val dataSearchList:ArrayList<Product> = arrayListOf()
        for (data in data){
            if (data.product_name.toLowerCase().contains(text.toLowerCase())) {
                dataSearchList.add(data);
            }
        }
        adapter.setSearchList(dataSearchList);
    }
}