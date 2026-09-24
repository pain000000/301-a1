package com.example.rapidrecall

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


@Composable
fun SummaryScreen(
    mainScreen: () -> Unit,
    modifier: Modifier = Modifier,
    attemptList: MutableList<Attempt>
) {

    var totalAttempts by remember { mutableIntStateOf(0) }
    var rightAttempts by remember { mutableIntStateOf(0) }
    var correctPercentage by remember { mutableStateOf("")}

    Column(
        modifier = modifier
    ) {

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "SUMMARY",
                fontSize = 80.sp
            )
            Text(
                text = "SCREEN",
                fontSize = 80.sp
            )
        }

        // Calculate required parameters here once
        LaunchedEffect(Unit) {
            totalAttempts = attemptList.size

            for (attempt in attemptList) {
                if (attempt.check == "Correct!") {
                    rightAttempts += 1
                }
            }

            if (totalAttempts != 0) {
                val percentage = (rightAttempts / totalAttempts) * 100
                correctPercentage = percentage.toString()
            }

        }

        Spacer(modifier = Modifier.height(200.dp))
        // display here
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Correct Attempts: $rightAttempts",
                fontSize = 20.sp
            )
            Text(
                text = "Total Attempts: $totalAttempts",
                fontSize = 20.sp
            )
            Text(
                text =
                    if (correctPercentage != "") "Correct Percentage: $correctPercentage%" else
                    "Correct Percentage: 0%",
                fontSize = 20.sp
            )
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


@Preview(
    showBackground = true
)
@Composable
fun SummaryScreenPreview() {
    SummaryScreen(
        mainScreen = {},
        modifier = Modifier.fillMaxSize(),
        attemptList = mutableListOf()
    )
}