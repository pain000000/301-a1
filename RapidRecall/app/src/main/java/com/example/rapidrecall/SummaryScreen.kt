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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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
        Spacer(modifier = Modifier.height(40.dp))
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "SUMMARY",
                fontSize = 70.sp,
                fontWeight = FontWeight.Bold,
                color = DMAUVE
            )
            Spacer(modifier = Modifier.height(30.dp))
            Text(
                text = "Some Statistics About This Session Below",
                fontSize = 30.sp,
                color = PURPLE,
                lineHeight = 40.sp,
                textAlign = TextAlign.Center
            )
        }

        // Calculate required parameters here once
        totalAttempts = attemptList.size

        for (attempt in attemptList) {
            if (attempt.check == "Correct!") {
                rightAttempts += 1
            }
        }

        if (totalAttempts > 0) {
            val percentage = (rightAttempts.toFloat() / totalAttempts.toFloat()) * 100.0
            correctPercentage = "%.1f".format(percentage)
        }


        Spacer(modifier = Modifier.height(200.dp))
        // display here
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Correct Attempts: $rightAttempts",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = RINDIGO
            )
            Text(
                text = "Total Attempts: $totalAttempts",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = RINDIGO
            )
            Text(
                text =
                    if (correctPercentage != "") "Win Percentage: $correctPercentage%" else
                    "Win Percentage: 0%",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = RINDIGO
            )
        }
        Spacer(modifier = Modifier.weight(1f))

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            MenuButton(
                c1 = RINDIGO,
                width = 120,
                fontSize = 20,
                text = "BACK",
                cmd = mainScreen
            )

        }
        Spacer(modifier = Modifier.height(50.dp))
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