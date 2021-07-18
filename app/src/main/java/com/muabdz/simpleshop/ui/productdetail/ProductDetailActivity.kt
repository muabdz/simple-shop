package com.muabdz.simpleshop.ui.productdetail

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.app.ShareCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.muabdz.simpleshop.R
import com.muabdz.simpleshop.data.ProductEntity
import com.muabdz.simpleshop.databinding.ActivityProductDetailBinding

class ProductDetailActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var viewBinding: ActivityProductDetailBinding
    private lateinit var viewModel: ProductDetailViewModel
    private var shareText: String? = null

    companion object {
        const val EXTRAS_PRODUCT = "extrasProduct"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityProductDetailBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        setSupportActionBar(viewBinding.toolbar)

        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val productExtras = intent.getParcelableExtra<ProductEntity>(EXTRAS_PRODUCT)

        viewBinding.ibFavorite.setOnClickListener(this)
        viewBinding.btnBuy.setOnClickListener(this)
        viewBinding.progressBar.visibility = View.VISIBLE
        viewBinding.gContent.visibility = View.INVISIBLE

        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[ProductDetailViewModel::class.java]
        viewModel.getProductDetail().observe(this, { product ->
            viewBinding.progressBar.visibility = View.GONE
            viewBinding.gContent.visibility = View.VISIBLE
            populateProductDetail(product)
            shareText = getString(R.string.wording_share_product, product.title, product.price)
        })

        if (productExtras == null) finish()
        viewModel.product.postValue(productExtras)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.product_detail_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
            R.id.btn_share -> {
                val mimeType = "text/plain"
                ShareCompat.IntentBuilder(this)
                    .setType(mimeType)
                    .setChooserTitle(getString(R.string.share_this_product))
                    .setText(shareText)
                    .startChooser()
            }
        }
        return false
    }


    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.ib_favorite -> {
                val product = viewModel.product.value
                product!!.loved = !product.loved
                viewModel.product.postValue(product)
            }
            R.id.btn_buy -> {
                viewModel.purchaseProduct()
                finish()
            }
        }
    }

    private fun populateProductDetail(product: ProductEntity) {
        viewBinding.tvProductName.text = product.title
        viewBinding.tvDescription.text = product.description
        viewBinding.tvPrice.text = product.price
        if (product.loved) {
            viewBinding.ibFavorite.setImageDrawable(AppCompatResources.getDrawable(this, R.drawable.ic_heart_filled))
        } else {
            viewBinding.ibFavorite.setImageDrawable(AppCompatResources.getDrawable(this, R.drawable.ic_heart_outline))
        }

        Glide.with(this).load(product.imageUrl)
            .apply(RequestOptions.placeholderOf(R.drawable.ic_image_loading)
                .error(R.drawable.ic_image_error)).into(viewBinding.ivProduct)
    }
}