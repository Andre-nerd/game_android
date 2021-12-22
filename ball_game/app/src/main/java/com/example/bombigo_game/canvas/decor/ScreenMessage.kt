package com.example.bombigo_game.canvas.decor

import android.content.Context
import android.graphics.*

class ScreenMessage(
    context: Context,
    sizeText: Float,
) {

    private var fontPaint = Paint(Paint.CURSOR_AFTER).apply {
        textSize = sizeText
        color = Color.BLACK
        typeface = Typeface.createFromAsset(context.assets, "ARCADE.TTF")
        Paint.Align.LEFT
    }
    private var x = 0f
    private var y = 0f

    fun setXY(dx: Float, dy: Float) {
        x = dx
        y = dy
    }

    fun draw(canvas: Canvas, message: String) {
        val matrix = Matrix()
        matrix.postTranslate(x, y)
        canvas.drawText(message, x, y, fontPaint)
    }
}