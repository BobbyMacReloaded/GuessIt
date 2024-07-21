package com.example.guessit.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.guessit.R
import com.example.guessit.ui.theme.BlueDark
import com.example.guessit.ui.theme.YellowDark
import kotlinx.coroutines.delay


@Composable
fun GuessingGameScreen (
    viewModel: MainViewModel
){
    val state by viewModel.state.collectAsState()

    val focusRequester  =remember{FocusRequester()}
    val context = LocalContext.current
    when(state.gateStage){
        GameStage.WON ->{
       Column (
           modifier = Modifier
               .fillMaxSize()
               .background(BlueDark)
               .padding(vertical = 20.dp)
       ){
           WinOrLoseDialog(
               text = "Congratulations\n you have won",
               resetGame = {
                   viewModel.resetGame()
               },
               buttonText = "Play again",
               mysteryNumber = state.mysteryNumber,
               image = painterResource(id = R.drawable.ic_trophy)

           )
       }

        }
        GameStage.LOSE -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(BlueDark)
                    .padding(vertical = 20.dp)
            )   {
                WinOrLoseDialog(
                    text = "Play Again\n you have lost",
                    resetGame = {
                        viewModel.resetGame()
                    },
                    buttonText = "Try again",
                    mysteryNumber = state.mysteryNumber,
                    image = painterResource(id = R.drawable.ic_trophy)

                )
            }
        }
        GameStage.PLAYING -> {
            ScreenContent(state = state ,
                onValueChange = {
                    viewModel.updateTextField(userNo = it)
                },
                onEnterButtonClicked = {
                    viewModel.onUserInput(
                        context = context,
                        userNumber = state.userNumber)
                })
        }
    }


}
@Composable
fun ScreenContent(
   state: GuessingGameState,
   onValueChange:(String)->Unit,
   onEnterButtonClicked:()->Unit
){


    val focusRequester  =remember{FocusRequester()}
    val context = LocalContext.current
    LaunchedEffect(key1 = Unit) {
        delay(500)
        focusRequester.requestFocus()
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BlueDark)
            .padding(vertical = 20.dp)
    ) {
        Text(
            modifier = Modifier.padding(horizontal = 10.dp,
                vertical = 80.dp),
            text = buildAnnotatedString {
                append("No of guess left:")

                withStyle(style = SpanStyle(color = Color.White)){
                    append("${state.noOfGuessLeft}")
                }
            },
            color = YellowDark,
            fontSize = 18.sp)
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ){
            state.guessedNumberList.forEach {number->
                Text(text = "$number",
                    color = YellowDark,
                    fontSize = 42.sp,
                    modifier = Modifier.padding(end = 20.dp))
            }
        }
        Text(text = state.hintDescription,
            color = Color.White,
            fontSize = 20.sp,
            lineHeight = 30.sp,
            fontStyle = FontStyle.Italic,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(40.dp))
        OutlinedTextField(value = state.userNumber, onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(focusRequester)
                .padding(horizontal = 40.dp),
            textStyle = TextStyle(
                textAlign = TextAlign.Center,
                fontSize = 48.sp
            ),
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color.Blue,


                ),

            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {

                 onEnterButtonClicked()
                }
            )
        )

        Spacer(modifier = Modifier.height(40.dp))
        Button(
            modifier = Modifier
                .align(Alignment.End)
                .padding(end = 40.dp)
                .clip(RectangleShape),

            colors = ButtonDefaults.buttonColors(
                containerColor = YellowDark,
                contentColor = Color.Black
            ),
            onClick = {
              onEnterButtonClicked()

            }) {
            Text(text = "Enter", fontSize = 18.sp)
        }
    }
}
@Preview
@Composable
fun Prev(){
    GuessingGameScreen(
        viewModel = MainViewModel()
    )
}