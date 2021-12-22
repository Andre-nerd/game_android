package com.example.ball_game.canvas.draw_cells

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint

class Cue(
    context: Context,
    id: Int,
    idImage: Int,
    idTransformImage: Int
) : ImpulseSource(context, id, idImage, idTransformImage) {
    override fun draw(canvas: Canvas, paint: Paint) {
        if (isDrawing) {
            canvas.drawLine(x, y, xStop, yStop, paint)
        }
    }
}
