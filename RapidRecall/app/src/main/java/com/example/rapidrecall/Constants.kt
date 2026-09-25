package com.example.rapidrecall

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val QLILAC = Color(0xffD391FA)
val DMAUVE = Color(0xffC364FA)
val PURPLE = Color(0xffA230ED)
val RINDIGO = Color(0xff6B00D7)
val IBLUE = Color(0xff3E00B3)
val RNAVY = Color(0xff190087)

// This enum class is used to name the different routes for the screen
enum class RapidScreen() {
    MainMenu,
    Game,
    Log,
    Summary
}
// menuButton
@Composable
fun MenuButton(
    cmd: () -> Unit,
    c1: Color,
    text: String,
    width: Int,
    fontSize: Int
) {
    Button(
        onClick = cmd,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            contentColor = Color.White
        ),
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier.width(width.dp)
            .height(60.dp)
            .background(color = c1, shape = RoundedCornerShape(8.dp)),

        ) {
        Text(
            text = text,
            fontSize = fontSize.sp
        )
    }
}