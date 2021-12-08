package com.example.ball_game.canvas.draw_decor

import android.graphics.Canvas
import android.graphics.Color.WHITE
import android.graphics.Paint

class TotalScore {
    var xStart = 0f
    var yStart = 0f
    var nameField = ""
    private var scoreString = 0

    private val fontPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        textSize = 50f
        style = Paint.Style.FILL
        color = WHITE
    }

    fun setScore(value: Int) {
        scoreString = value
    }

    fun draw(canvas: Canvas) {
        canvas.drawText("$nameField: $scoreString", xStart, yStart, fontPaint)
    }
}