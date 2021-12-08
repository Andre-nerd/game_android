package com.example.ball_game.canvas.draw_cells

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import androidx.annotation.DrawableRes
import com.example.ball_game.canvas.motionContracts.MotionContracts
import com.example.ball_game.canvas.motionContracts.ScreenSize
import kotlin.math.abs

open class Entity(
    context: Context,
    id: Int,
    @DrawableRes idImage: Int,
    @DrawableRes idImageTransform: Int
) : Cell(context, id, idImage, idImageTransform) {
    var impX: Float = 0f
    var impY: Float = 0f
    var mass: Float = 100f
    var vX: Float = 0f
    var vY: Float = 0f


    override fun draw(paint: Paint?, canvas: Canvas) {// рисуем картинку
        countTick()
        checkBorderXY()
        calcImp()
        calcXY(impX, impY)
        downSpeed()
        matrix.setScale(scaleX, scaleY)
        matrix.postTranslate(xStart, yStart)
        canvas.drawBitmap(bitmap, matrix, paint)
    }

    private fun checkBorderXY() {
        if (xStart <= 0 || xStart >= ScreenSize.width - 2.6 * getAttribute().radius) {
            impX = -impX
            vX = -vX
        }
        if (yStart <= 0 || yStart >= ScreenSize.height - 100) {
            impY = -impY
            vY = -vY
        }
    }

    fun setImp(dImpX: Float, dImpY: Float) {
        impX += dImpX
        impY += dImpY
    }

    private fun calcXY(impX: Float, impY: Float) {
        vX += impX / mass
        vY += impY / mass
        xStart += vX // Всегда единица времени, поэтому время исключаем
        yStart += vY
    }

    private fun calcImp() {
        impX -= MotionContracts.downImpX * impX
        impY -= MotionContracts.downImpY * impY
    }

    private fun downSpeed() {
        vX -= MotionContracts.downSpeedX * vX
        vY -= MotionContracts.downSpeedY * vY
        if (abs(vX) < MotionContracts.minSpeed) vX = 0f
        if (abs(vY) < MotionContracts.minSpeed) vY = 0f
    }
}

