package com.example.rapidrecall

import android.app.Activity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.milliseconds

@Composable
fun MainMenu(
   gameScreen: () -> Unit,
   logScreen: () -> Unit,
   summaryScreen: () -> Unit,
   modifier: Modifier = Modifier
) {

    val context = (LocalContext.current) as? Activity

    var rapid by remember { mutableStateOf("") }
    var recall by remember { mutableStateOf("") }

    // This LaunchedEffect controls the "RAPID RECALL" title animation
    LaunchedEffect(Unit) {
        while(true) {
            rapid = "RAPID"
            recall = ""
            delay(1000L.milliseconds)

            rapid = ""
            recall = "RECALL"
            delay(1000L.milliseconds)
        }

    }

    Column(
        modifier = modifier
    ) {
        Spacer(modifier = Modifier.height(230.dp))

        // This Column stores the "RAPID RECALL" title
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = if (rapid != "") rapid else recall,
                fontSize = 90.sp,
                fontWeight = FontWeight.Bold,
                color = DMAUVE
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        // This Column stores the 4 buttons to navigate game, log, or summary screen, or exit
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            MenuButton(gameScreen, PURPLE, "PLAY", 200,30)
            Spacer(modifier = Modifier.height(10.dp))

            MenuButton(logScreen, RINDIGO, "LOG", 200, 30)
            Spacer(modifier = Modifier.height(10.dp))

            MenuButton(summaryScreen, IBLUE, "SUMMARY", 200, 30)
            Spacer(modifier = Modifier.height(10.dp))

            MenuButton({ context?.finish() }, RNAVY, "EXIT", 200, 30)
            Spacer(modifier = Modifier.height(100.dp))
        }
    }
}

@Preview(
    showBackground = true
)
@Composable
fun MainMenuPreview() {
    MainMenu(
        logScreen = {},
        gameScreen = {},
        summaryScreen = {},
        modifier = Modifier.fillMaxSize()
    )
}