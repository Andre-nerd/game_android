package com.example.ball_game.canvas.draw_cells

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import androidx.annotation.DrawableRes
import com.example.bombigo_game.contracts_and_commons.MotionContracts.momentumMultiplier
import com.example.ball_game.canvas.motionContracts.ScreenSize
import com.example.bombigo_game.canvas.entities.Cell

open class ImpulseSource(
    context: Context,
    id: Int,
    @DrawableRes idImage: Int,
    @DrawableRes idImageTransform: Int
) : Cell(context, idImage) {

    var xStop: Float = 0f
    var yStop: Float = 0f
    var isDrawing = false

    fun setStartPosition(x0: Float, y0: Float) {
        x = x0
        y = y0
    }

    fun setStopPosition(x1: Float, y1: Float) {
        xStop = x1
        yStop = y1
    }

    open fun getImpulse(): Pair<Float, Float> {
        val impX = (x - xStop) / ScreenSize.width * momentumMultiplier
        val impY = (y - yStop) / ScreenSize.height * momentumMultiplier
        return (impX to impY)
    }

    open fun draw(canvas: Canvas, paint: Paint) {
        TODO("Not yet implemented")
    }

    override fun draw(paint: Paint?, canvas: Canvas) {
        TODO("Not yet implemented")
    }
}
