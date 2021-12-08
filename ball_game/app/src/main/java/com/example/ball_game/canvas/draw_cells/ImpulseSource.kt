package com.example.ball_game.canvas.draw_cells

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import androidx.annotation.DrawableRes
import com.example.ball_game.canvas.motionContracts.MotionContracts.momentumMultiplier
import com.example.ball_game.canvas.motionContracts.ScreenSize
import com.example.ball_game.data.Impulse

open class ImpulseSource(
    context: Context,
    id: Int,
    @DrawableRes idImage: Int,
    @DrawableRes idImageTransform: Int
) : Cell(context, id, idImage, idImageTransform) {

    var xStop: Float = 0f
    var yStop: Float = 0f

    fun setStartPosition(x0: Float, y0: Float) {
        xStart = x0
        yStart = y0
    }

    fun setStopPosition(x1: Float, y1: Float) {
        xStop = x1
        yStop = y1
    }

    open fun getImpulse(): Impulse {
        val impX = (xStart - xStop) / ScreenSize.width * momentumMultiplier
        val impY = (yStart - yStop) / ScreenSize.height * momentumMultiplier
        return Impulse(impX, impY)
    }

    open fun draw(canvas: Canvas, paint: Paint) {
        TODO("Not yet implemented")
    }
}