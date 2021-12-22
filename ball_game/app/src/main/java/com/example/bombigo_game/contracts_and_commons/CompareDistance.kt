package com.example.bombigo_game.contracts_and_commons

import com.example.ball_game.canvas.motionContracts.ScreenSize
import com.example.bombigo_game.canvas.entities.Cell
import com.example.bombigo_game.canvas.entities.Entity
import kotlin.math.sqrt

fun compareXY(
    ball: Cell,
    button: Cell
): Boolean {
    val s = sqrt(
        (ball.xCenter - button.xCenter) * (ball.xCenter - button.xCenter) +
                (ball.yCenter - button.yCenter) * (ball.yCenter - button.yCenter)
    )
    return s <= (button.radius + ball.radius) * 0.8f
}

fun whatEntityChoice(x: Float, y: Float, listEntity: List<Entity>): Int {
    var id = 0
    var sPrevious = ScreenSize.height * 2f
    listEntity.forEach { it ->
        val s = sqrt((x - it.x) * (x - it.x) + (y - it.y) * (y - it.y))
        if (s < sPrevious) {
            sPrevious = s
            id = it.id
        }
    }
    return id
}
