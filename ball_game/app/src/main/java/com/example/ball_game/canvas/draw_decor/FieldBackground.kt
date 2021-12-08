package com.example.ball_game.canvas.draw_decor

import android.content.Context
import android.graphics.*
import com.example.ball_game.R

class FieldBackground(context: Context) {
    private val bitmap: Bitmap = BitmapFactory.decodeResource(
        context.resources,
        R.drawable.cloth
    )

    fun draw(paint: Paint?, canvas: Canvas, matrix: Matrix) {// рисуем картинку
        canvas.drawBitmap(bitmap, matrix, paint)
    }
}