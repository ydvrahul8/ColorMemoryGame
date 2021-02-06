package com.example.colormemory.view.common.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.example.colormemory.utils.OnItemClickListener
import com.example.colormemory.R
import com.example.colormemory.models.ColorCards
import javax.inject.Inject

class MyImageAdapter @Inject constructor(val requestManager: RequestManager) : RecyclerView.Adapter<MyImageAdapter.MyImageViewHolder>() {

    private var items: ArrayList<ColorCards> = ArrayList()
    private var clickListener: OnItemClickListener? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyImageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.image_item, parent, false)
        return MyImageViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: MyImageViewHolder, position: Int) {
        holder.bindTo(position, items[position])
    }

    fun setData(data: ArrayList<ColorCards>, onItemClickListener: OnItemClickListener) {
        items = data
        clickListener = onItemClickListener
        notifyDataSetChanged()
    }

    inner class MyImageViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val image = view.findViewById<ImageView>(R.id.imageView)

        fun bindTo(position: Int, data: ColorCards) {
            requestManager.load(R.drawable.card_bg).into(image)
            image.setOnClickListener {
                clickListener?.onItemClick(position, data, image)
            }
        }

    }

}