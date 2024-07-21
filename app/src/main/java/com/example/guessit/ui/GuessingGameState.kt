package com.example.guessit.ui

data class GuessingGameState(
    val userNumber:String ="",
    val noOfGuessLeft:Int=5,
    val guessedNumberList: List<Int> = emptyList(),
    val mysteryNumber :Int = (1..99).random(),
    val hintDescription:String ="Guess \n the mystery  number between\n 0 and 100.",
    val gateStage:GameStage = GameStage.PLAYING

)
enum class GameStage{
    WON,
    LOSE,
    PLAYING

}