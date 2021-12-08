package com.example.ball_game.canvas.draw_cells

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import androidx.annotation.DrawableRes

open class ScoreBonus(
    context: Context,
    id: Int,
    @DrawableRes idImage: Int,
    @DrawableRes idImageTransform: Int,
) : Cell(context, id, idImage, idImageTransform) {

    fun setPosition(x0: Float, y0: Float) {
        xStart = x0
        yStart = y0
    }

    fun showBonus(paint: Paint?, canvas: Canvas) {
        if (!isDrawing) pTick = tick
        isDrawing = true
        draw(paint, canvas)
    }

    override fun draw(paint: Paint?, canvas: Canvas) {// рисуем картинку
        matrix.setScale(scaleX, scaleY)
        matrix.postTranslate(xStart, yStart)
        if (isDrawing) {
            canvas.drawBitmap(bitmap, matrix, paint)
        }
        countTick()
        moveBonus()
    }

    private fun moveBonus() {
        yStart -= 20
    }
}