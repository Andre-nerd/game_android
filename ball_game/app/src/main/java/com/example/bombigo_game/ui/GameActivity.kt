package com.example.bombigo_game.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.bombigo_game.canvas.SceneSurfaceView
import com.example.game_custom_view.ui.MainFragment.Companion.levelTag


class GameActivity : AppCompatActivity() {
    private var scene: android.view.SurfaceView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val args = intent.extras
        if (args != null) {
            scene = SceneSurfaceView(
                this,
                attributeSet = null,
                0,
                args.getInt(levelTag),
                ::doWhenEndSession
            )
        }
        setContentView(scene)
    }

    private fun doWhenEndSession() {
        scene = null
        this.finish()
    }
}
