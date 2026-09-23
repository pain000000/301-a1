package com.example.rapidrecall

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun MainMenu(
   gameScreen: () -> Unit,
   logScreen: () -> Unit,
   summaryScreen: () -> Unit,
   modifier: Modifier = Modifier
) {

    val context = (LocalContext.current) as? Activity


    Column(
        modifier = modifier
    ) {

        Spacer(modifier = Modifier.height(220.dp))

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
                onClick = gameScreen,
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.width(200.dp).height(60.dp)
            ) {
                Text(
                    text = "PLAY",
                    fontSize = 30.sp
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Button(
                onClick =  logScreen,
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.width(200.dp).height(60.dp)
            ) {
                Text(
                    text = "LOG",
                    fontSize = 30.sp
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Button(
                onClick = summaryScreen,
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.width(200.dp).height(60.dp)
            ) {
                Text(
                    text = "SUMMARY",
                    fontSize = 30.sp
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Button(
                onClick = { context?.finish() },
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.width(200.dp).height(60.dp)

            ) {
                Text(
                    text = "EXIT",
                    fontSize = 30.sp
                )
            }

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