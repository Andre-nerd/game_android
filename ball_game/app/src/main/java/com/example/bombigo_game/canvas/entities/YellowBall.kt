package com.example.bombigo_game.canvas.entities

import android.content.Context
import com.example.bombigo_game.R

class YellowBall(
    context: Context,
    id: Int
) : Entity(context = context, id = id, R.drawable.pingpong_shadow, R.drawable.pingpong_shadow) {
    init {
        setScale(0.3f)
        mass = 100f
        distanceForBorder = 160
        correctCenter = -10f  // Коррекция положения центра при наличии тени фигуры
    }
}

