package com.example.guessit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.guessit.ui.GuessingGameScreen
import com.example.guessit.ui.MainViewModel
import com.example.guessit.ui.theme.GuessItTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GuessItTheme {
                val viewModel = viewModel<MainViewModel>()
               GuessingGameScreen(
                   viewModel = viewModel
               )
            }
        }
    }
}

