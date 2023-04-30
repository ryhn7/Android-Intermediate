package com.dicoding.picodiploma.productdetail.dataSource

import android.content.Context
import com.dicoding.picodiploma.productdetail.R
import com.dicoding.picodiploma.productdetail.model.ProductModel

class RemoteDataSource(private val context: Context) {

    fun getDetailProduct(): ProductModel {
        return ProductModel(
            name = context.getString(R.string.name),
            store = context.getString(R.string.store),
            size = context.getString(R.string.size),
            color = context.getString(R.string.color),
            desc = context.getString(R.string.description),
            price = context.getString(R.string.price),
            date = context.getString(R.string.date),
            rating = context.getString(R.string.rating),
            countRating = context.getString(R.string.countRating),
            image = R.drawable.shoes
        )
    }
}