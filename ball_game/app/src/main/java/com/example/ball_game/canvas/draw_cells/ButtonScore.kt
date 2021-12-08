package com.example.ball_game.canvas.draw_cells

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import androidx.annotation.DrawableRes

class ButtonScore(
    context: Context,
    id: Int,
    @DrawableRes idImage: Int,
    @DrawableRes idImageTransform: Int,
    val bonus: ScoreBonus
) : Cell(context, id, idImage, idImageTransform) {

    override fun draw(paint: Paint?, canvas: Canvas) {// рисуем картинку
        matrix.setScale(scaleX, scaleY)
        matrix.postTranslate(xStart, yStart)
        if (isTransform) {
            canvas.drawBitmap(bitmapTransform, matrix, paint)
        } else {
            canvas.drawBitmap(bitmap, matrix, paint)
        }
        countTick() // Считает время сущестования объекта
        restoreForm(time = 50) // Востанавливаем первоначальную форму через 5 циклов,
        // если она была изменена transformForm()
    }

    override fun restoreForm(time: Int) {
        if (isTransform && tick - pTick > time) {
            scaleX += 0.02f
            scaleY += 0.02f
            isTransform = false
            bonus.run {
                yStart = super.yStart
                isDrawing = false
            }
        }
    }
}