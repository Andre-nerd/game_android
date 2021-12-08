package com.example.ball_game.extention_and_common

import android.content.Context
import com.example.ball_game.R
import com.example.ball_game.canvas.draw_cells.*
import com.example.ball_game.canvas.motionContracts.ScreenSize

fun setListOfBall(context: Context): List<Entity> {
    return listOf(
        YellowBall(context = context, id = 0).apply {
            xStart = ScreenSize.width / 2f
            yStart = ScreenSize.height / 1.3f
        }
    )
}

fun setListOfButton(context: Context): List<ButtonScore> {
    return listOf(
        ButtonScore(
            context, id = 0,
            R.drawable.red_button_shadow,
            R.drawable.red_button_light_shadow,
            ScoreBonus(context, id = 0, R.drawable.minus_handred, R.drawable.minus_handred)
        ).apply {
            xStart = 300f
            yStart = 300f
            bonus.setPosition(xStart, yStart)
        },
        ButtonScore(
            context, id = 0,
            R.drawable.red_button_shadow,
            R.drawable.red_button_light_shadow,
            ScoreBonus(context, id = 0, R.drawable.minus_handred, R.drawable.minus_handred)
        ).apply {
            xStart = ScreenSize.width / 2.5f
            yStart = ScreenSize.height / 2f
            bonus.setPosition(xStart, yStart)
        },
        ButtonScore(
            context, id = 1,
            R.drawable.yellow_button_shadow,
            R.drawable.blue_button_shadow,
            ScoreBonus(context, id = 0, R.drawable.plus_bonus, R.drawable.plus_bonus)
        ).apply {
            xStart = ScreenSize.width / 1.5f
            yStart = ScreenSize.height / 2f
            bonus.setPosition(xStart, yStart)
        },
        ButtonScore(
            context, id = 1,
            R.drawable.yellow_button_shadow,
            R.drawable.blue_button_shadow,
            ScoreBonus(context, id = 0, R.drawable.plus_bonus, R.drawable.plus_bonus)
        ).apply {
            xStart = ScreenSize.width / 100f
            yStart = ScreenSize.height / 1.3f
            bonus.setPosition(xStart, yStart)
        },
        ButtonScore(
            context, id = 1,
            R.drawable.yellow_button_shadow,
            R.drawable.blue_button_shadow,
            ScoreBonus(context, id = 0, R.drawable.plus_bonus, R.drawable.plus_bonus)
        ).apply {
            xStart = 120f
            yStart = 80f
            bonus.setPosition(xStart, yStart)
        },
    )
}

fun initCue(context: Context): Cue {
    return Cue(context, id = 0, R.drawable.pingpong_shadow, R.drawable.pingpong_shadow)
}