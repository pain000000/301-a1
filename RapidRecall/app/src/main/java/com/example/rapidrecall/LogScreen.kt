package com.example.rapidrecall

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
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

        Spacer(modifier = Modifier.height(200.dp))

        LazyColumn(
            modifier = Modifier.height(400.dp).fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
                ) {
           itemsIndexed(items = attemptList) {
                 index, attempt -> FormatAttempt(attempt, index)
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
fun FormatAttempt(attempt: Attempt, index: Int) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
            .padding(16.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(if (attempt.check == "Correct!") Color.Green else Color.Red)
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Text("ATTEMPT ${index+1}")
        Text("Check: ${attempt.check}")
        Text("Sequence Length: ${attempt.seqLength}")
        Text("Your Guess: ${attempt.input}")
        Text("Sequence: ${attempt.sequence}")
        Text("Timestamp: ${attempt.timestamp}")
        Spacer(modifier = Modifier.height(16.dp))
    }
    Spacer(modifier = Modifier.height(10.dp))
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