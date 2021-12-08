package com.example.ball_game.canvas.draw_cells

import android.content.Context
import com.example.ball_game.R

class YellowBall(
    context: Context,
    id: Int
) : Entity(context = context, id = id, R.drawable.pingpong_shadow, R.drawable.pingpong_shadow) {
    init {
        scaleX = 0.3f
        scaleY = 0.3f
        mass = 100f
    }
}