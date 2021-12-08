package com.example.ball_game.canvas.draw_cells

import android.content.Context
import android.graphics.*
import androidx.annotation.DrawableRes
import com.example.ball_game.canvas.motionContracts.MotionContracts

open class Cell(
    context: Context,
    val id: Int,
    @DrawableRes idImage: Int,
    @DrawableRes idImageTransform: Int
) {
    var xStart: Float = 0f
    var yStart: Float = 0f
    var scaleX: Float = 0.5f
    var scaleY: Float = 0.5f
    var tick = 0L
    var pTick = 0L
    var isTransform = false
    var isDrawing: Boolean = false
    val matrix = Matrix()

    val bitmap: Bitmap = BitmapFactory.decodeResource(
        context.resources,
        idImage
    )
    val bitmapTransform: Bitmap = BitmapFactory.decodeResource(
        context.resources,
        idImageTransform
    )

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

    open fun draw(paint: Paint?, canvas: Canvas) {
        // Must be override
    }

    fun getAttribute(): AttributeFigure {
        return AttributeFigure(
            MotionContracts.picSize / 2 + xStart,
            MotionContracts.picSize / 2 + yStart,
            MotionContracts.picSize * scaleX
        )
    }

    fun countTick() {
        tick += 1
    }
}