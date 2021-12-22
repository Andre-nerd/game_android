package com.example.bombigo_game.canvas.entities

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import androidx.annotation.DrawableRes
import com.example.bombigo_game.canvas.decor.Figure
import kotlin.math.*

class BitmapFigure(
    context: Context,
    id: Int,
    @DrawableRes idImage: Int,
) : Figure() {

    private val bitmap: Bitmap = BitmapFactory.decodeResource(
        context.resources,
        idImage
    )

    override val xCenter: Float
        get() = this.width() / 2f + x
    override val yCenter: Float
        get() = this.height() / 2f + y

    val radius: Float
        get() = sqrt((width() / 2).pow(2) + (height() / 2).pow(2))

    val alfa: Float
        get() = acos(radius / width()) * 180 / PI.toFloat()

    fun rotate(dAlfa: Float, canvas: Canvas, paint: Paint) {
        val dx = -radius * cos((dAlfa + alfa) * PI.toFloat() / 180)
        val dy = dx * tan((dAlfa + alfa) * PI.toFloat() / 180)
        matrix.setScale(scaleX, scaleY)
        matrix.preRotate(dAlfa)
        matrix.postTranslate(xCenter + dx, yCenter + dy)
        this.draw(paint, canvas)
    }

    override fun draw(paint: Paint, canvas: Canvas) {
        canvas.drawBitmap(bitmap, matrix, paint)
    }

    fun width() = bitmap.width * scaleX
    fun height() = bitmap.height * scaleY
}
