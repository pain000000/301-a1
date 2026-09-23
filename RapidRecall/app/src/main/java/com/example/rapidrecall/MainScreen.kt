package com.example.rapidrecall

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun MainMenu(
   gameScreen: () -> Unit,
   logScreen: () -> Unit,
   summaryScreen: () -> Unit,
   exitScreen: () -> Unit,
   modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "RAPID",
                fontSize = 90.sp
            )
            Text(
                text = "RECALL",
                fontSize = 90.sp
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = gameScreen
            ) {
                Text("PLAY")
            }

            //TODO: Implement
            Button(
                onClick =  logScreen
            ) {
                Text("LOG")
            }
            //TODO: Implement
            Button(
                onClick = summaryScreen
            ) {
                Text("SUMMARY")
            }

            //TODO: Implement
            Button(
                onClick = {}
            ) {
                Text("EXIT")
            }
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
        exitScreen = {},
        modifier = Modifier.fillMaxSize()
    )
}