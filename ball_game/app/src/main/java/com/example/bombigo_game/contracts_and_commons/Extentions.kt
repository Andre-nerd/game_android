package com.example.bombigo_game.contracts_and_commons

import android.graphics.Canvas
import com.example.bombigo_game.canvas.decor.Score
import com.example.bombigo_game.canvas.entities.ButtonOnGameField

fun showScores(list: List<Score>, canvas: Canvas) {
    list.forEach {
        it.draw(canvas)
    }
}

fun setVibration(it: ButtonOnGameField, timeLimitSession: Int) {
    when (timeLimitSession) {
        1500 -> {
            it.setVibrationLimit(3)
        }
        700 -> {
            it.setVibrationLimit(5)
        }
        300 -> {
            it.setVibrationLimit(10)
        }
    }
}
