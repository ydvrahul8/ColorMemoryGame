package com.example.colormemory.view.common.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.colormemory.R
import com.example.colormemory.databinding.HighscoreItemBinding
import com.example.colormemory.models.User
import javax.inject.Inject

class HighScoreAdapter @Inject constructor() : RecyclerView.Adapter<HighScoreAdapter.HighScoreViewHolder>() {
    private var items = ArrayList<User>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HighScoreViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: HighscoreItemBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.highscore_item, parent, false)
        return HighScoreViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: HighScoreViewHolder, position: Int) {
        holder.bindTo(position, items[position])
    }

    fun setItems(data: ArrayList<User>) {
        items = data
        notifyDataSetChanged()
    }

    inner class HighScoreViewHolder(val binding: HighscoreItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindTo(position: Int, user: User) {
            user.id = position + 1
            binding.user = user
        }
    }
}