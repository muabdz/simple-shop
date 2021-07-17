package com.muabdz.simpleshop.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.muabdz.simpleshop.R
import com.muabdz.simpleshop.data.CategoryEntity
import com.muabdz.simpleshop.databinding.ItemsCategoriesBinding

class CategoriesAdapter: RecyclerView.Adapter<CategoriesAdapter.CategoryViewHolder>() {

    private var listCategory = ArrayList<CategoryEntity>()

    fun setCategories(categories: List<CategoryEntity>?) {
        if (categories == null) return
        this.listCategory.clear()
        this.listCategory.addAll(categories)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val itemsCategoriesBinding = ItemsCategoriesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(itemsCategoriesBinding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = listCategory[position]
        holder.bind(category)
    }

    override fun getItemCount(): Int = listCategory.size

    inner class CategoryViewHolder(private val viewBinding: ItemsCategoriesBinding): RecyclerView.ViewHolder(viewBinding.root) {
        fun bind(category: CategoryEntity) {
            with(viewBinding) {
                tvCategory.text = category.name

                Glide.with(itemView.context).load(category.imageUrl)
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_image_loading)
                        .error(R.drawable.ic_image_error)).into(ivCategory)
            }
        }
    }
}