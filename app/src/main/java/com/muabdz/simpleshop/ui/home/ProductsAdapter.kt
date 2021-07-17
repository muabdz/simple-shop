package com.muabdz.simpleshop.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.muabdz.simpleshop.R
import com.muabdz.simpleshop.data.ProductEntity
import com.muabdz.simpleshop.databinding.ItemsProductsBinding

class ProductsAdapter(private val callback: ProductListCallback): RecyclerView.Adapter<ProductsAdapter.ProductViewHolder>() {

    private var listProducts = ArrayList<ProductEntity>()

    fun setProducts(products: List<ProductEntity>?) {
        if (products == null) return
        this.listProducts.clear()
        this.listProducts.addAll(products)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val itemsProductsBinding = ItemsProductsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(itemsProductsBinding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = listProducts[position]
        holder.bind(product)
    }

    override fun getItemCount(): Int = listProducts.size

    inner class ProductViewHolder(private val viewBinding: ItemsProductsBinding): RecyclerView.ViewHolder(viewBinding.root) {
        fun bind(productEntity: ProductEntity) {
            with(viewBinding) {
                tvProductName.text = productEntity.title
                if (productEntity.loved) {
                    ibFavorite.setImageDrawable(AppCompatResources.getDrawable(itemView.context, R.drawable.ic_heart_filled))
                } else {
                    ibFavorite.setImageDrawable(AppCompatResources.getDrawable(itemView.context, R.drawable.ic_heart_outline))
                }
                itemView.setOnClickListener {
                    callback.onProductClicked(productEntity)
                }
                Glide.with(itemView.context).load(productEntity.imageUrl)
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_image_loading)
                        .error(R.drawable.ic_image_error)).into(ivProduct)
            }
        }
    }
}