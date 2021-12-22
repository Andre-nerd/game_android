package com.example.bombigo_game.canvas.entities

import android.content.Context
import android.graphics.Canvas
import android.graphics.Matrix
import android.graphics.Paint
import androidx.annotation.DrawableRes
import com.example.bombigo_game.contracts_and_commons.MotionContracts
import com.example.ball_game.canvas.motionContracts.ScreenSize
import kotlin.math.abs

open class Entity(
    context: Context,
    val id: Int,
    @DrawableRes idImage: Int,
    @DrawableRes idImageTransform: Int
) : Cell(context, idImage) {
    var impX: Float = 0f
    var impY: Float = 0f
    var mass: Float = 100f
    var vX: Float = 0f
    var vY: Float = 0f
    var distanceForBorder = 0
    private val matrix = Matrix()

    override fun draw(paint: Paint?, canvas: Canvas) {// рисуем картинку
        checkBorderXY()
        calcImp()
        calcXY(impX, impY)
        downSpeed()
        matrix.setScale(scaleX, scaleY)
        matrix.postTranslate(x, y)
        canvas.drawBitmap(bitmap, matrix, paint)
    }

    private fun checkBorderXY() {
        if (x <= 0 || x >= (ScreenSize.width - distanceForBorder).toFloat()) {
            if (x < 0) x = 0f
            if (x > ScreenSize.width - distanceForBorder) {
                x = (ScreenSize.width - distanceForBorder).toFloat()
            }
            impX = -impX
            vX = -vX
        }
        if (y <= 0 || y >= ScreenSize.height - distanceForBorder) {
            if (y < 0) y = 0f
            if (y > ScreenSize.height - distanceForBorder) {
                y = (ScreenSize.height - distanceForBorder).toFloat()
            }
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
        x += vX // Всегда единица времени, поэтому время исключаем
        y += vY
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

