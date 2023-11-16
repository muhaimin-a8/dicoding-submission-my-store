package com.example.mystore

import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.viewpager2.widget.ViewPager2
import com.example.mystore.adapter.CarouselDetailProductAdapter
import com.example.mystore.data.Product
import com.example.mystore.databinding.ActivityDetailProductBinding

class DetailProductActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailProductBinding
    private val vp2Carousel: ViewPager2 by lazy {
        findViewById(R.id.vp2_carousel)
    }

    private val product: Product?
        get() {
            if (Build.VERSION.SDK_INT >= 33) {
                return intent.getParcelableExtra(EXTRA_PRODUCT, Product::class.java)
            } else {
                @Suppress("DEPRECATION")
                return intent.getParcelableExtra(EXTRA_PRODUCT)
            }
        }

    companion object {
        const val EXTRA_PRODUCT = "extra_product"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // return if product is null
        if (product == null) return

        // set action bar
        supportActionBar?.title = product?.title
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

         // adapter
        vp2Carousel.adapter = CarouselDetailProductAdapter(product!!.images)

        binding.tvImagesCount.text = "${product?.images?.size} images"

        binding.tvTitle.text = product!!.title
        binding.tvPrice.text = "$ ${product?.price}"
        binding.tvDescription.text = product!!.description

        binding.includeDescription.textDescBrand.text = ": ${product?.brand}"
        binding.includeDescription.textDescStock.text = ": ${product?.stock}"
        binding.includeDescription.textCategory.text = ": ${product?.category}"

        binding.btnDetailBuy.setOnClickListener {
            Toast.makeText(applicationContext, "Feature not ready yet", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_product_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.action_share -> {
                val intent = Intent(Intent.ACTION_SEND)
                intent.type = "text/plain"
                intent.putExtra(Intent.EXTRA_TEXT, "Hi, checkout this ${product?.title } only $ ${product?.price}")

                startActivity(Intent.createChooser(intent, "Share"))
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
