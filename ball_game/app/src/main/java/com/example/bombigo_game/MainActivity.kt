package com.example.bombigo_game

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.bombigo_game.canvas.entities.data.SessionResult
import com.example.game_custom_view.ui.MainFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        SessionResult.readLevelFromSharedPref(this)
        val fragmentMain = MainFragment()
        fragmentMain.let {
            supportFragmentManager.beginTransaction()
                .replace(R.id.mainContainer, it)
                .commit()
        }
    }
}
