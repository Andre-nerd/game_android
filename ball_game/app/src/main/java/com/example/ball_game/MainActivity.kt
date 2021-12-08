package com.example.ball_game

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.ball_game.canvas.DrawTextSurface
import com.example.ball_game.canvas.motionContracts.ScreenSize


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getScreenSize()
        setContentView(DrawTextSurface(this, attributeSet = null, 0))
    }

    private fun getScreenSize() {
        val display = windowManager.defaultDisplay
        ScreenSize.height = display.height - 150
        ScreenSize.width = display.width
    }
}