package com.example.guessit.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.guessit.R
import com.example.guessit.ui.theme.BlueDark
import com.example.guessit.ui.theme.YellowDark

@Composable
fun WinOrLoseDialog(
text:String,
buttonText:String,
mysteryNumber:Int,
image:Painter,
resetGame:()->Unit
) {
    Dialog(onDismissRequest = { resetGame() }) {
        

    Column(
        modifier = Modifier
            .size(300.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(YellowDark),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = text,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center)
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "The mystery Number is $mysteryNumber ",
            fontSize = 26.sp,
            fontFamily = FontFamily.Cursive,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center)
Image(painter = image,
    contentDescription = "won",
    modifier = Modifier.size(80.dp))
Button(
colors = ButtonDefaults.buttonColors(
    containerColor = BlueDark,
    contentColor = Color.White
),
    onClick = { resetGame() }) {
    Text(text = buttonText,
        fontSize = 18.sp)
}
    }
}
    }
@Preview
@Composable
fun DialogPrev(){
    WinOrLoseDialog(
        text = "Congratulations\n you have won",
        resetGame = {},
        buttonText = "Play again",
        mysteryNumber = 32,
        image = painterResource(id = R.drawable.ic_trophy)
        
    )
}

@Preview
@Composable
fun LoseDialogPrev(){
    WinOrLoseDialog(
        text = "Play Again\n you have lost",
        resetGame = {},
        buttonText = "Try again",
        mysteryNumber = 32,
        image = painterResource(id = R.drawable.ic_trophy)

    )
}










