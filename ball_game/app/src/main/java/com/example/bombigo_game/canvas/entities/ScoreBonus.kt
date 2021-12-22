package com.example.bombigo_game.canvas.entities

import android.content.Context
import android.graphics.Canvas
import android.graphics.Matrix
import android.graphics.Paint
import androidx.annotation.DrawableRes

open class ScoreBonus(
    context: Context,
    id: Int,
    @DrawableRes idImage: Int,
    @DrawableRes idImageTransform: Int,
) : Cell(context, idImage) {
    var isDrawing = false
    private val matrix = Matrix()

    init {
        setScale(0.3f)
    }

    fun setPosition(x0: Float, y0: Float) {
        x = x0
        y = y0
    }

    override fun draw(paint: Paint?, canvas: Canvas) {// рисуем картинку
        matrix.setScale(scaleX, scaleY)
        matrix.postTranslate(x, y)
        if (isDrawing) {
            canvas.drawBitmap(bitmap, matrix, paint)
        }
        moveBonus()
    }

    private fun moveBonus() {
        y -= 20
    }

    fun showBonus(paint: Paint?, canvas: Canvas) {
        if (!isDrawing) pTick = tick
        isDrawing = true
        draw(paint, canvas)
    }
}
