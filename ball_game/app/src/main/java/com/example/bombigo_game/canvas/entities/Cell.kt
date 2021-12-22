package com.example.bombigo_game.canvas.entities

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import androidx.annotation.DrawableRes
import kotlin.math.pow
import kotlin.math.sqrt

abstract class Cell(
    context: Context,
    @DrawableRes idImage: Int,
) {
    var x: Float = 0f
    var y: Float = 0f
    var scaleX: Float = 1f
    var scaleY: Float = 1f
    var tick = 0L
    var pTick = 0L
    var isTransform = false
    val radius: Float
        get() = sqrt((width() / 2).pow(2) + (height() / 2).pow(2)) * 0.6f

    val bitmap: Bitmap = BitmapFactory.decodeResource(
        context.resources,
        idImage
    )

    private val xSize = (bitmap.width).toFloat()
    private val ySize = (bitmap.width).toFloat()
    var correctCenter = 0f // Коррекция положения центра при наличии тени фигуры
    val xCenter: Float
        get() = x + xSize / 2 * scaleX + correctCenter
    val yCenter: Float
        get() = y + ySize / 2 * scaleY + correctCenter

    fun setXY(valueX: Float, valueY: Float) {
        this.x = valueX
        this.y = valueY
    }

    fun setScale(k: Float) {
        scaleX = k
        scaleY = k
    }

    abstract fun draw(paint: Paint?, canvas: Canvas)

    fun width() = bitmap.width * scaleX
    fun height() = bitmap.height * scaleY


    fun countTick(): Long {
        tick += 1
        if (tick > 2000000000) tick = 0
        return tick
    }

    fun transformForm() {
        if (!isTransform) {
            scaleX -= 0.02f
            scaleY -= 0.02f
            pTick = tick
            isTransform = true
        }
    }

    open fun restoreForm(time: Int) {
        if (isTransform && tick - pTick > time) {
            scaleX += 0.02f
            scaleY += 0.02f
            isTransform = false
        }
    }
}
