package com.example.myapplication.presentation

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.data.GameRepositoryImpl
import com.example.myapplication.domain.entity.GameSettings
import com.example.myapplication.domain.entity.Level
import com.example.myapplication.domain.usecases.GenerateQuestionUseCase
import com.example.myapplication.domain.usecases.GetGameSettingUseCase

class GameViewModel : ViewModel() {

    private lateinit var gameSettings: GameSettings
    private lateinit var level: Level

    private val repository = GameRepositoryImpl

    private val generateQuestionUseCase = GenerateQuestionUseCase(repository)
    private val getGameSettingsUseCase = GetGameSettingUseCase(repository)

    private val _formattedTime = MutableLiveData<String>()
    val formattedTime: LiveData<String>
        get() = _formattedTime

    fun startGame(level: Level){
        this.level
        this.gameSettings = getGameSettingsUseCase(level)
    }

    private fun startTimer(){
        val timer = object : CountDownTimer(
            gameSettings.gameTimeInSeconds * MILLIS_IN_SECONDS,
            MILLIS_IN_SECONDS
        ){
            override fun onTick(millisUntilFinished: Long) {
                TODO("Not yet implemented")
            }

            override fun onFinish() {
                finishGame()
            }
        }
    }

    private fun formatTime(millisUntilFinished: Long): String {
        val seconds = millisUntilFinished/MILLIS_IN_SECONDS
        val minutes = seconds/ SECONDS_IN_MINUTES
        val leftSeconds = seconds - (minutes * SECONDS_IN_MINUTES)
        return String.format("%02d:%02d", minutes, leftSeconds)
    }

    private fun finishGame() {

    }


    companion object {
        private const val MILLIS_IN_SECONDS = 1000L
        private const val SECONDS_IN_MINUTES = 60
    }
}