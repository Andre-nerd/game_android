package com.example.bombigo_game.canvas.entities

import android.content.Context
import android.graphics.*
import androidx.annotation.DrawableRes

class ButtonOnGameField(
    context: Context,
    val id: Int,
    @DrawableRes idImage: Int,
    @DrawableRes idImageTransform: Int,
    val bonus: ScoreBonus
) : Cell(context, idImage) {

    private val matrix = Matrix()
    private var vibrationXY = 0
    private var limitVibration = 0

    private val bitmapTransform: Bitmap = BitmapFactory.decodeResource(
        context.resources,
        idImageTransform
    )
    var isPressed = false

    override fun draw(paint: Paint?, canvas: Canvas) {// рисуем картинку
        vibration()
        matrix.setScale(scaleX, scaleY)
        matrix.postTranslate(x, y)
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
                y = super.y
                isDrawing = false
            }
        }
    }

    fun setVibrationLimit(value: Int) {
        limitVibration = value
    }

    private fun vibration() {
        if (tick % 2 == 0L) {
            vibrationXY = (-limitVibration..limitVibration).random()
            setXY(x + vibrationXY, y + vibrationXY)
        } else {
            setXY(x - vibrationXY, y - vibrationXY)
        }
    }
}
