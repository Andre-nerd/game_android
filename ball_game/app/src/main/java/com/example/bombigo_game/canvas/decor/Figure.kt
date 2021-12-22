package com.example.bombigo_game.canvas.decor

import android.graphics.Canvas
import android.graphics.Matrix
import android.graphics.Paint

abstract class Figure {
    var x: Float = 0f
    var y: Float = 0f
    var scaleX: Float = 1f
    var scaleY: Float = 1f
    abstract val xCenter: Float
    abstract val yCenter: Float
    val matrix = Matrix().apply {
        setScale(scaleX, scaleY)
    }

    fun setXY(dx: Float, dy: Float) {
        x = dx
        y = dy
    }

    fun setScale(k: Float) {
        scaleX = k
        scaleY = k
    }

    abstract fun draw(paint: Paint, canvas: Canvas)

}