package com.example.bombigo_game.canvas

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.view.MotionEvent
import android.view.SurfaceHolder
import com.example.ball_game.canvas.motionContracts.BonusContracts
import com.example.bombigo_game.R
import com.example.bombigo_game.canvas.decor.Score
import com.example.bombigo_game.canvas.decor.ScreenMessage
import com.example.bombigo_game.canvas.entities.ButtonOnGameField
import com.example.bombigo_game.canvas.entities.Entity
import com.example.bombigo_game.canvas.entities.data.Coordinates
import com.example.bombigo_game.canvas.entities.data.SceneColor
import com.example.bombigo_game.canvas.entities.data.SessionResult
import com.example.bombigo_game.contracts_and_commons.*

class DrawThread(
    private val context: Context,
    private val surfaceHolder: SurfaceHolder,
    level: Int,
    private val doWhenEndSession: () -> Unit
) : Thread() {
    private var running = false
    private val paint = Paint()
    private val coordinates = Coordinates()
    private val listOfBall = setListOfBall(context, coordinates.setBallCoordinates(level))
    private val cue = initCue(context)
    private val listOfButton = setListOfButton(context, coordinates.setButtonCoordinates(level))
    private val energy = initEnergy(context, value = setLevelEnergy(level))
    private var timeToExplosion = initTimerTable(context, timeLimit = durationSession(level))
    private val listOfScores = listOf<Score>(timeToExplosion, energy)
    private var idChoiceEntity = 0
    private var event: MotionEvent? = null
    private var colorScene = SceneColor.blue
    private val screenMessage = ScreenMessage(context, 200f)
    private var isFinish = false
    private var endMessage = ""
    private val totalScore = ScreenMessage(context, 100f)
    private var totalScoreMessage = ""
    private val levelImp = setLevelImpulse(level)


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
                    if (isFinish) theEnd()
                    listOfBall[idChoiceEntity].run {
                        cue.setStartPosition(this.xCenter, yCenter)
                    }
                    cue.isDrawing = true
                    idChoiceEntity = whatEntityChoice(event!!.x, event!!.y, listOfBall)
                }
                1 -> {
                    listOfBall[idChoiceEntity].setImp(
                        cue.getImpulse().first * levelImp,
                        cue.getImpulse().second * levelImp
                    )
                    cue.isDrawing = false
                }
                2 -> {
                    listOfBall[idChoiceEntity].run {
                        cue.setStartPosition(xCenter, yCenter)
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
                drawMainScene(canvas)
                if (isFinish) {
                    drawFinishScreen(canvas)
                }
                if (timeToExplosion.value == 0 || energy.value == 0) {
                    finishSession(context)
                }
            } finally {
                if (canvas != null) {
                    timeToExplosion.addScore(-1)
                    surfaceHolder.unlockCanvasAndPost(canvas)
                }
            }
        }
    }

    private fun finishSession(context: Context) {
        var scoreValue = 0
        var isResultWin = false
        if (energy.value == 0) {
            scoreValue = 20000 - timeToExplosion.value
            isResultWin = true
            endMessage = context.resources.getString(R.string.you_win)
        } else {
            endMessage = context.resources.getString(R.string.game_over)
        }
        SessionResult.run {
            this.score = scoreValue
            this.isWin = isResultWin
            totalScoreMessage = context.resources.getString(R.string.score) + this.score.toString()
        }

        energy.stop()
        timeToExplosion.stop()
        isFinish = true
    }

    private fun theEnd() {
        setRunning(false)
        doWhenEndSession()
    }

    private fun drawFinishScreen(canvas: Canvas) {
        screenMessage.setXY(canvas.width / 2f - 450f, canvas.height / 2f)
        screenMessage.draw(canvas, endMessage)
        totalScore.setXY(50f, canvas.height * 3 / 4f)
        totalScore.draw(canvas, totalScoreMessage)
    }


    private fun drawMainScene(canvas: Canvas) {
        canvas.drawColor(colorScene)
        handleTheEvent(canvas, paint)
        showScores(listOfScores, canvas)
        listOfButton.forEach {
            it.draw(paint, canvas)
            setVibration(it, timeToExplosion.value)
        }
        listOfBall.forEach {
            it.draw(paint, canvas)
        }
        checkWhichBallsPressButton(listOfBall, listOfButton, canvas)
    }

    private fun checkWhichBallsPressButton(
        listBall: List<Entity>,
        listButton: List<ButtonOnGameField>,
        canvas: Canvas
    ) {
        var changeEnergy = 0
        listBall.forEach { ball ->
            listButton.forEach { button ->
                button.isPressed = compareXY(ball, button)
                if (button.isPressed) {
                    button.transformForm()
                    if (!button.bonus.isDrawing && ball.vX != 0f && ball.vY != 0f) {
                        when (button.id) {
                            0 -> changeEnergy = BonusContracts.bonus_100
                            1 -> changeEnergy -= BonusContracts.bonus_100
                        }
                        energy.addScore(changeEnergy)
                    }
                    if (ball.vX != 0f || ball.vY != 0f) button.bonus.showBonus(paint, canvas)
                }
            }
        }
    }
}

