package com.example.ball_game.canvas

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView

class DrawTextSurface(
    context: Context, attributeSet: AttributeSet?,
    defaultStyle: Int
) :
    SurfaceView(context, attributeSet, defaultStyle), SurfaceHolder.Callback {
    private lateinit var drawThread: DrawThread

    init {
        holder.addCallback(this)
    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        drawThread = DrawThread(context, getHolder());
        drawThread.setRunning(true);
        drawThread.start();
    }

    override fun surfaceChanged(
        holder: SurfaceHolder, format: Int, width:
        Int, height: Int
    ) {
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event != null) {
            drawThread.setEvent(event)
        }
        return true
    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {
        var retry = true
        drawThread.setRunning(false)
        while (retry) {
            try {
                drawThread.join()
                retry = false
            } catch (e: InterruptedException) {
            }
        }
    }
}