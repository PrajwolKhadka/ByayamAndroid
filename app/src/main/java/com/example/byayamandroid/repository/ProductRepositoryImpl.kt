package com.example.byayamandroid.repository

import com.example.byayamandroid.model.ProductModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class ProductRepositoryImpl: ProductRepository {

    val database: FirebaseDatabase= FirebaseDatabase.getInstance()
    val ref: DatabaseReference=database.reference.child("products")


    override fun addProduct(productModel: ProductModel, callback: (Boolean, String) -> Unit) {
        var id=ref.push().key.toString()

    }

    override fun updateProduct(productId: String, data: MutableMap<String, Any>) {
        TODO("Not yet implemented")
    }

    override fun deleteProduct(productId: String, callback: (Boolean, String) -> Unit) {
        TODO("Not yet implemented")
    }

    override fun getProductById(
        productId: String,
        callback: (ProductModel?, Boolean, String) -> Unit
    ) {
        TODO("Not yet implemented")
    }

    override fun getAllProduct(callback: (List<ProductModel?>, Boolean, String) -> Unit) {
        TODO("Not yet implemented")
    }
}