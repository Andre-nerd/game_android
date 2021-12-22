package com.example.bombigo_game.canvas.entities.data

import com.example.ball_game.canvas.motionContracts.ScreenSize

class Coordinates {

    fun setBallCoordinates(level: Int): List<Pair<Float, Float>> {
        var list = emptyList<Pair<Float, Float>>()
        when (level) {
            1, 2, 3 -> list = listOf(
                (50f to ScreenSize.height / 3f),
            )
            4, 5, 6 -> list = listOf(
                (50f to ScreenSize.height / 3f),
                (ScreenSize.width - 250f to ScreenSize.height / 3f),
            )
        }
        return list
    }

    fun setButtonCoordinates(level: Int): List<CoordinatesButton> {
        var list = emptyList<CoordinatesButton>()
        when (level) {
            1 -> list = listOf(
                CoordinatesButton(ScreenSize.width / 2.5f, ScreenSize.height / 5f, 0),
                CoordinatesButton(ScreenSize.width / 2.5f, ScreenSize.height * 3 / 5f, 1),
            )
            2 -> list = listOf(
                CoordinatesButton(ScreenSize.width / 2.5f, ScreenSize.height / 2f, 0),
                CoordinatesButton(ScreenSize.width / 1.5f, ScreenSize.height / 2f, 1),
                CoordinatesButton(ScreenSize.width / 3f, 300f, 1),
                CoordinatesButton(ScreenSize.width / 1.6f, ScreenSize.height / 4f, 0),
            )
            3 -> list = listOf(
                CoordinatesButton(ScreenSize.width / 2f - 100f, ScreenSize.height / 6f, 0),
                CoordinatesButton(ScreenSize.width / 2f - 100f, ScreenSize.height * 2 / 6f, 1),
                CoordinatesButton(ScreenSize.width / 2f - 100f, ScreenSize.height * 3 / 6f, 0),
                CoordinatesButton(ScreenSize.width / 2f - 100f, ScreenSize.height * 4 / 6f, 1),
            )
            4 -> list = listOf(
                CoordinatesButton(ScreenSize.width / 2f - 100f, ScreenSize.height / 6f, 0),
                CoordinatesButton(ScreenSize.width / 2f - 100f, ScreenSize.height * 2 / 6f, 1),
                CoordinatesButton(ScreenSize.width / 2f - 100f, ScreenSize.height * 3 / 6f, 0),
                CoordinatesButton(ScreenSize.width / 2f - 100f, ScreenSize.height * 4 / 6f, 1),
            )
            5 -> list = listOf(
                CoordinatesButton(ScreenSize.width / 2f - 100f, ScreenSize.height / 8f, 0),
                CoordinatesButton(ScreenSize.width / 2f - 100f, ScreenSize.height * 2 / 8f, 1),
                CoordinatesButton(ScreenSize.width / 2f - 100f, ScreenSize.height * 4 / 8f, 0),
                CoordinatesButton(ScreenSize.width / 2f - 100f, ScreenSize.height * 6 / 8f, 1),
                CoordinatesButton(100f, ScreenSize.height * 3 / 6f, 0),
                CoordinatesButton(ScreenSize.width / 2f + 150f, ScreenSize.height * 6 / 8f, 1),
            )
            6 -> list = listOf(
                CoordinatesButton(ScreenSize.width / 2f - 100f, ScreenSize.height / 8f, 0),
                CoordinatesButton(ScreenSize.width / 2f - 100f, ScreenSize.height * 2 / 8f, 1),
                CoordinatesButton(ScreenSize.width / 2f - 100f, ScreenSize.height * 4 / 8f, 0),
                CoordinatesButton(ScreenSize.width / 2f - 100f, ScreenSize.height * 6 / 8f, 1),
                CoordinatesButton(100f, 120f, 0),
                CoordinatesButton(ScreenSize.width - 250f, 120f, 1),
                CoordinatesButton(ScreenSize.width - 250f, ScreenSize.height * 6 / 8f, 0),
                CoordinatesButton(100f, ScreenSize.height * 4 / 8f, 1),
            )
        }
        return list
    }
}