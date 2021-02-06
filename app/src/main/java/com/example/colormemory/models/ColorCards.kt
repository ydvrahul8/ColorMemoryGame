package com.example.colormemory.models

import com.example.colormemory.R

data class ColorCards(val colorTag: String? = null, val backgroundImage: Int? = null) {

    companion object {

        fun getData(): ArrayList<ColorCards> {
            val cardList = ArrayList<ColorCards>()
            cardList.add(
                ColorCards(
                    "color1",
                    R.drawable.colour1
                )
            )
            cardList.add(
                ColorCards(
                    "color2",
                    R.drawable.colour2
                )
            )
            cardList.add(
                ColorCards(
                    "color3",
                    R.drawable.colour3
                )
            )
            cardList.add(
                ColorCards(
                    "color4",
                    R.drawable.colour4
                )
            )
            cardList.add(
                ColorCards(
                    "color5",
                    R.drawable.colour5
                )
            )
            cardList.add(
                ColorCards(
                    "color6",
                    R.drawable.colour6
                )
            )
            cardList.add(
                ColorCards(
                    "color7",
                    R.drawable.colour7
                )
            )
            cardList.add(
                ColorCards(
                    "color8",
                    R.drawable.colour8
                )
            )

            val list = ArrayList<ColorCards>()
            list.addAll(cardList)
            list.addAll(cardList)

            list.shuffle()

            return list
        }

    }

}