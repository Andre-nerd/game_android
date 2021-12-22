package com.example.game_custom_view.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.bombigo_game.R
import com.example.bombigo_game.canvas.entities.data.SessionResult
import com.example.bombigo_game.databinding.FragmentMainBinding
import com.example.bombigo_game.ui.GameActivity


class MainFragment : Fragment(R.layout.fragment_main) {
    private var level = SessionResult.level
    private var score = SessionResult.score
    private var isStart = true
    private var backgroundImage = R.drawable.begin_game
    private lateinit var binding: FragmentMainBinding

    override fun onResume() {
        super.onResume()
        score = SessionResult.score
        setLevel()
        setTextOnScreen()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentMainBinding.bind(view)
        setTextOnScreen()
        binding.run {
            playButton.setOnClickListener {
                val intent = Intent(requireContext(), GameActivity::class.java)
                intent.putExtra(levelTag, level)
                isStart = false
                startActivity(intent)
            }
            resetButton.setOnClickListener {
                resetAll()
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setTextOnScreen() {
        binding.backgroundImage.setImageResource(backgroundImage)
        binding.run {
            levelTextView.text = resources.getString(R.string.level) + level.toString()
            scoreTextView.text = resources.getString(R.string.score) + score.toString()
        }
    }

    private fun setLevel() {
        if (SessionResult.isWin) {
            level += 1
            SessionResult.level = this.level
            SessionResult.saveLevelToSharedPref(requireContext())
        }
    }

    private fun resetAll() {
        SessionResult.isWin = false
        SessionResult.score = 0
        level = 1
        score = 0
        backgroundImage = R.drawable.begin_game
        SessionResult.deleteLevelFromSharedPref(requireContext())
        setTextOnScreen()
    }

    companion object {
        const val levelTag = "level"
    }
}

