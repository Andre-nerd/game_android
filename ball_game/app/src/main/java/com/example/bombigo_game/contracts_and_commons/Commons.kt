package com.example.bombigo_game.contracts_and_commons

import android.content.Context
import com.example.ball_game.canvas.draw_cells.Cue
import com.example.ball_game.canvas.motionContracts.ScreenSize
import com.example.bombigo_game.R
import com.example.bombigo_game.canvas.decor.Score
import com.example.bombigo_game.canvas.entities.ButtonOnGameField
import com.example.bombigo_game.canvas.entities.Entity
import com.example.bombigo_game.canvas.entities.ScoreBonus
import com.example.bombigo_game.canvas.entities.YellowBall
import com.example.bombigo_game.canvas.entities.data.CoordinatesButton


fun setListOfBall(context: Context, list: List<Pair<Float, Float>>): List<Entity> {
    val createdList = emptyList<Entity>().toMutableList()
    var i = 0
    list.forEach {
        createdList += YellowBall(context = context, id = i).apply {
            x = it.first
            y = it.second
        }
        i += 1
    }
    return createdList
}

fun setListOfButton(context: Context, list: List<CoordinatesButton>): List<ButtonOnGameField> {
    val createdList = emptyList<ButtonOnGameField>().toMutableList()

    list.forEach {
        when (it.id) {
            0 -> {
                createdList += ButtonOnGameField(
                    context,
                    it.id,
                    R.drawable.red_button,
                    R.drawable.red_button_light,
                    ScoreBonus(context, id = 0, R.drawable.plus_bonus, R.drawable.plus_bonus)
                )
                    .apply {
                        x = it.x
                        y = it.y
                        setScale(0.4f)
                        bonus.setPosition(x, y)
                    }
            }
            1 -> {
                createdList += ButtonOnGameField(
                    context,
                    it.id,
                    R.drawable.yellow_button,
                    R.drawable.yellow_button_light,
                    ScoreBonus(context, id = 0, R.drawable.minus_bonus, R.drawable.minus_bonus)
                )
                    .apply {
                        x = it.x
                        y = it.y
                        setScale(0.4f)
                        bonus.setPosition(x, y)
                    }
            }
        }
    }
    return createdList
}

fun initCue(context: Context): Cue {
    return Cue(context, id = 0, R.drawable.pingpong_shadow, R.drawable.pingpong_shadow)
}


fun initEnergy(context: Context, value: Int) = Score(context, 100f).apply {
    xStart = 50f
    yStart = 100f
    nameField = context.resources.getString(R.string.energy)
    setScore(value)
}

fun initTimerTable(context: Context, timeLimit: Int) = Score(context, 200f).apply {
    xStart = ScreenSize.width / 2f
    yStart = ScreenSize.height - 100f
    setScore(timeLimit)
    nameField = ""
}

fun durationSession(level: Int): Int {
    return when (level) {
        1, 2 -> 4000
        3, 4, 5 -> 6000
        6, 7, 8 -> 8000
        10 -> 10000
        else -> 0
    }
}

fun setLevelImpulse(level: Int): Float {
    return when (level) {
        1, 2 -> 1f
        3, 4, 5 -> 1.1f
        6, 7, 8 -> 1.3f
        10 -> 1.5f
        else -> 0f
    }
}

fun setLevelEnergy(level: Int): Int {
    return when (level) {
        1 -> 400
        2 -> 500
        3, 4, 5 -> 1000
        6, 7, 8 -> 1200
        10 -> 2000
        else -> 0
    }
}
