package com.muabdz.simpleshop.ui.productlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.muabdz.simpleshop.R
import com.muabdz.simpleshop.data.ProductEntity
import com.muabdz.simpleshop.databinding.ItemsProductsSearchBinding

class ProductListAdapter(private val callback: ProductListCallback): RecyclerView.Adapter<ProductListAdapter.SearchProductViewHolder>() {

    private var listProducts = ArrayList<ProductEntity>()

    fun setProducts(products: List<ProductEntity>?) {
        if (products == null) return
        this.listProducts.clear()
        this.listProducts.addAll(products)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchProductViewHolder {
        val viewBinding = ItemsProductsSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchProductViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: SearchProductViewHolder, position: Int) {
        val product = listProducts[position]
        holder.bind(product)
    }

    override fun getItemCount(): Int = listProducts.size

    inner class SearchProductViewHolder(private val viewBinding: ItemsProductsSearchBinding): RecyclerView.ViewHolder(viewBinding.root) {
        fun bind(product: ProductEntity) {
            with(viewBinding) {
                tvProductName.text = product.title
                tvPrice.text = product.price

                itemView.setOnClickListener {
                    callback.onProductClicked(product)
                }

                Glide.with(itemView).load(product.imageUrl)
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_image_loading)
                        .error(R.drawable.ic_image_error)).into(ivProduct)
            }
        }
    }
}