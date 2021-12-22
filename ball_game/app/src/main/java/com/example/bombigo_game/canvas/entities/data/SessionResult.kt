package com.example.bombigo_game.canvas.entities.data

import android.content.Context

object SessionResult {
    var isWin = false
    var score = 0
    var level: Int = 0

    private const val CURRENT_LEVEL = "current level"
    private const val LEVEL_STRING = "level string"
    fun saveLevelToSharedPref(context: Context) {
        val editor = context.getSharedPreferences(
            CURRENT_LEVEL,
            Context.MODE_PRIVATE
        ).edit()
        editor.putInt(LEVEL_STRING, SessionResult.level)
        editor.apply()
    }

    fun deleteLevelFromSharedPref(context: Context) {
        val editor = context.getSharedPreferences(
            CURRENT_LEVEL,
            Context.MODE_PRIVATE
        ).edit()
        editor.remove(LEVEL_STRING)
        editor.apply()
    }

    fun readLevelFromSharedPref(context: Context): Boolean {
        val levelValue = context.getSharedPreferences(
            CURRENT_LEVEL,
            Context.MODE_PRIVATE
        )
            .getInt(LEVEL_STRING, 1)
        return run {
            SessionResult.level = levelValue
            true
        }
    }
}
