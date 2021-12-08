package com.example.ball_game.canvas

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color.YELLOW
import android.graphics.Matrix
import android.graphics.Paint
import android.view.MotionEvent
import android.view.SurfaceHolder
import com.example.ball_game.R
import com.example.ball_game.canvas.draw_cells.AttributeFigure
import com.example.ball_game.canvas.draw_cells.Entity
import com.example.ball_game.canvas.draw_decor.FieldBackground
import com.example.ball_game.canvas.draw_decor.TotalScore
import com.example.ball_game.canvas.motionContracts.BonusContracts
import com.example.ball_game.canvas.motionContracts.ScreenSize
import com.example.ball_game.extention_and_common.initCue
import com.example.ball_game.extention_and_common.setListOfBall
import com.example.ball_game.extention_and_common.setListOfButton
import kotlin.math.sqrt

class DrawThread(
    context: Context,
    private val surfaceHolder: SurfaceHolder
) : Thread() {
    private var running = false
    private val paint = Paint().apply {
        strokeWidth = 2f
        color = YELLOW
        style = Paint.Style.FILL_AND_STROKE
    }
    private val matrix = Matrix()
    private val fieldBackground = FieldBackground(context)

    private val listOfBall = setListOfBall(context)
    private var event: MotionEvent? = null
    private val cue = initCue(context)
    private val listOfButton = setListOfButton(context)
    private val totalScore = TotalScore().apply {
        xStart = ScreenSize.width - 350f
        yStart = ScreenSize.height / 1f
        nameField = context.resources.getString(R.string.score)
    }
    private val recordScore = TotalScore().apply {
        xStart = 100f
        yStart = ScreenSize.height / 1f
        nameField = context.resources.getString(R.string.record)
        setScore(3500)
    }

    private var idChoiceEntity = 0
    private var scoreTotal = 0


    fun setRunning(running: Boolean) {
        this.running = running
    }

    fun setEvent(newEvent: MotionEvent?) {
        event = newEvent
    }

    private fun handleTheEvent(canvas: Canvas, paint: Paint) {
        val action = event?.action
        if (event != null) {
            when (action) {
                0 -> {
                    listOfBall[idChoiceEntity].run {
                        cue.setStartPosition(getAttribute().xCenter, getAttribute().yCenter)
                    }
                    cue.isDrawing = true
                    idChoiceEntity = whatEntityChoice(event!!.x, event!!.y)
                }
                1 -> {
                    listOfBall[idChoiceEntity].setImp(cue.getImpulse().impX, cue.getImpulse().impY)
                    cue.isDrawing = false
                }
                2 -> {
                    listOfBall[idChoiceEntity].run {
                        cue.setStartPosition(getAttribute().xCenter, getAttribute().yCenter)
                    }
                    cue.isDrawing = true
                    cue.setStopPosition(event!!.x, event!!.y)
                    cue.draw(canvas, paint)
                }
            }
            event = null
        }
    }

    override fun run() {
        var canvas: Canvas?
        while (running) {
            canvas = null
            try {
                canvas = surfaceHolder.lockCanvas(null)
                if (canvas == null) continue
                fieldBackground.draw(paint, canvas, matrix)
                totalScore.draw(canvas)
                recordScore.draw(canvas)
                handleTheEvent(canvas, paint)
                listOfButton.forEach {
                    it.draw(paint, canvas)
                }
                listOfBall.forEach {
                    it.draw(paint, canvas)
                }
                checkWhichBallsPressButton(listOfBall, canvas)
            } finally {
                if (canvas != null) {
                    surfaceHolder.unlockCanvasAndPost(canvas)
                }
            }
        }
    }

    private fun checkWhichBallsPressButton(listEntity: List<Entity>, canvas: Canvas) {
        listEntity.forEach { ball ->
            listOfButton.forEach { button ->
                val isPressedButton = compareXY(ball.getAttribute(), button.getAttribute())
                if (isPressedButton) {
                    button.transformForm()
                    if (!button.bonus.isDrawing) {
                        when (button.id) {
                            0 -> scoreTotal -= BonusContracts.bonus_100
                            1 -> scoreTotal += BonusContracts.bonus_100
                        }
                        totalScore.setScore(scoreTotal)
                    }
                    button.bonus.showBonus(paint, canvas)
                }
            }
        }
    }

    private fun compareXY(
        ball: AttributeFigure,
        button: AttributeFigure
    ): Boolean {
        val s = sqrt(
            (ball.xCenter - button.xCenter) * (ball.xCenter - button.xCenter) +
                    (ball.yCenter - button.yCenter) * (ball.yCenter - button.yCenter)
        )
        return s <= button.radius + ball.radius
    }

    private fun whatEntityChoice(x: Float, y: Float): Int {
        var id = 0
        var sPrevious = ScreenSize.height * 2f
        listOfBall.forEach { it ->
            val s = sqrt((x - it.xStart) * (x - it.xStart) + (y - it.yStart) * (y - it.yStart))
            if (s < sPrevious) {
                sPrevious = s
                id = it.id
            }
        }
        return id
    }
}