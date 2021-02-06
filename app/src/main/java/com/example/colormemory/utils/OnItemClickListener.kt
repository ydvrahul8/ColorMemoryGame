package com.example.colormemory.utils

import android.widget.ImageView
import com.example.colormemory.models.ColorCards

interface OnItemClickListener {
    fun onItemClick(index: Int, cards: ColorCards, view: ImageView)
}