package com.example.mystore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mystore.adapter.ListProductAdapter
import com.example.mystore.data.DataJson
import com.example.mystore.data.Product
import com.example.mystore.databinding.ActivityMainBinding
import com.example.mystore.utils.Utils
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class MainActivity : AppCompatActivity() {
    private lateinit var rvProducts: RecyclerView
    private lateinit var binding: ActivityMainBinding
//    private var currentPage: Int = 1
//    private val listProduct: List<Product>
//        get() {
//            val rawJson = Utils.readRawFile(applicationContext, R.raw.data)
//            return Json.decodeFromString<DataJson>(rawJson).products
//        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        rvProducts = binding.rvProducts

        // show recyclerview list
        rvProducts.layoutManager = GridLayoutManager(this, 2)
        val adapter = ListProductAdapter(loadData())
        rvProducts.adapter = adapter

        // endless scroll
        rvProducts.addOnScrollListener(object: RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (recyclerView.canScrollVertically(1)) {
                    adapter.listProduct += loadData()
                    adapter.notifyDataSetChanged()
//                    currentPage++
                }
            }
        })

        // set listener for each product
        adapter.setOnItemClickCallback(object: ListProductAdapter.OnItemClickCallback{
            override fun onItemClicked(product: Product) {
                val intent = Intent(this@MainActivity, DetailProductActivity::class.java)
                intent.putExtra(DetailProductActivity.EXTRA_PRODUCT, product)

                startActivity(intent)
            }

        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.about_page -> {
                startActivity(
                    Intent(this@MainActivity, AboutMeActivity::class.java)
                )
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun loadData(): List<Product> {
        val rawJson = Utils.readRawFile(applicationContext, R.raw.data)
        //        if(page * size >= products.size) {
//            currentPage = 0
////            products = products.shuffled()
//        }
//        return products.subList(page, page + size)

        return Json.decodeFromString<DataJson>(rawJson).products.shuffled()
    }
}
