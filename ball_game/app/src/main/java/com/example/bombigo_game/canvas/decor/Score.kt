package com.example.bombigo_game.canvas.decor

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Typeface

open class Score(val context: Context, private val sizeType: Float) {
    var xStart = 0f
    var yStart = 0f
    var nameField = ""
    var value = 0
    var isStop = false

    private var fontPaint = Paint(Paint.CURSOR_AFTER).apply {
        textSize = sizeType
        color = Color.BLACK
        typeface = Typeface.createFromAsset(context.assets, "ARCADE.TTF")
    }

    fun setPaint(value: Paint) {
        fontPaint = value
    }

    fun stop() {
        isStop = true
    }

    fun setScore(v: Int) {
        if (!isStop) value = v
    }

    fun addScore(value: Int) {
        if (!isStop) this.value += value
    }

    fun draw(canvas: Canvas) {
        canvas.drawText("$nameField: $value", xStart, yStart, fontPaint)
    }
}