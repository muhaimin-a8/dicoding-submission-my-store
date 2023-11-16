package com.example.mystore.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mystore.R
import com.example.mystore.data.Product

class ListProductAdapter(var listProduct: List<Product>): RecyclerView.Adapter<ListProductAdapter.ListViewHolder>() {
    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitle: TextView = itemView.findViewById(R.id.tv_item_title)
        val imgThumb: ImageView = itemView.findViewById(R.id.img_item_thumnail)
        val tvPrice: TextView = itemView.findViewById(R.id.tv_item_price)
    }

    private lateinit var onItemClickedCallback: OnItemClickCallback

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val v: View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_product, parent, false)
        return ListViewHolder(v)
    }

    override fun getItemCount(): Int = listProduct.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val product = listProduct[position]

        Glide.with(holder.itemView.context)
            .load(product.thumbnail)
            .into(holder.imgThumb)
        holder.tvTitle.text = product.title
        holder.tvPrice.text = "$ ${product.price}"

        holder.itemView.setOnClickListener {
            onItemClickedCallback.onItemClicked(product)
        }
    }



    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickedCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(product: Product)
    }
}