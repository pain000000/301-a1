package com.example.rapidrecall

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun LogScreen(
    mainScreen: () -> Unit,
    modifier: Modifier = Modifier,
    attemptList: MutableList<Attempt>
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
                text = "LOG",
                fontSize = 90.sp
            )
            Text(
                text = "SCREEN",
                fontSize = 90.sp
            )
        }

        Spacer(modifier = Modifier.height(300.dp))

        LazyColumn(
            modifier = Modifier.height(200.dp).fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
                ) {
           items(items = attemptList) {
                 attempt -> FormatAttempt(attempt)
             }
         }

        Spacer(modifier = Modifier.weight(1f))

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = mainScreen
            ) {
                Text("BACK")
            }

        }
    }
}

@Composable
fun FormatAttempt(attempt: Attempt) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text("ATTEMPT")
        Text("Sequence Length: ${attempt.seqLength}")
        Text("Your Guess: ${attempt.input}")
        Text("Sequence: ${attempt.sequence}")
        Text("Timestamp: ${attempt.timestamp}")
        Spacer(modifier = Modifier.height(20.dp))
    }
}

@Preview(
    showBackground = true
)
@Composable
fun LogScreenPreview() {
    LogScreen(
        mainScreen = {},
        modifier = Modifier.fillMaxSize(),
        attemptList = mutableListOf<Attempt>()
    )
}