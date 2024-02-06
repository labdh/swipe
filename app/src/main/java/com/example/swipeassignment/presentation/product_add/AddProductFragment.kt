package com.example.swipeassignment.presentation.product_add

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import com.example.swipeassignment.R
import com.example.swipeassignment.databinding.FragmentAddProductBinding
import com.example.swipeassignment.domain.model.Product
import com.example.swipeassignment.presentation.product_list.ProductlistVM
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddProductFragment : BottomSheetDialogFragment() {
    lateinit var binding: FragmentAddProductBinding
    lateinit var mainViewModel: AddProductVM
    var type:String = ""
    private var contract = registerForActivityResult(ActivityResultContracts.GetContent()){
        binding.imageView.setImageURI(it)
        binding.imageView.visibility = View.VISIBLE
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddProductBinding.inflate(inflater,container,false)
        mainViewModel = ViewModelProvider(this).get(AddProductVM::class.java)
        setProductTypeDropDown()
        binding.addImage.setOnClickListener { contract.launch("image/*") }
        binding.add.setOnClickListener {
            uploadtoApi()
        }
        return binding.root
    }

    private fun setProductTypeDropDown() {

        val item = listOf<String>("type1","type2","type3")
        binding.productType.setAdapter(ArrayAdapter<String>(requireContext(), R.layout.type_list_item, item))
        binding.productType.setOnItemClickListener { adapterView, view, i, l ->
            type = adapterView.getItemAtPosition(i).toString()
        }
    }

    fun uploadtoApi(){
        val name = binding.productName.text.toString()
        val price = binding.productPrice.text.toString()
        val tax = binding.productTax.text.toString()

        if(name.isNullOrBlank() || type.isNullOrBlank() || price.isNullOrBlank() || tax.isNullOrBlank()){
            Toast.makeText(context,"Fill all the required details of the product",Toast.LENGTH_LONG).show()
        }
        else{
            val product = Product("",price,name,type,tax)
            Log.e("checkaddfrag","upload to api initiate : ${product}")
            mainViewModel.addProduct(product)
        }
        dismiss()
        Toast.makeText(context,"Product Added!!",Toast.LENGTH_LONG).show()
    }
}