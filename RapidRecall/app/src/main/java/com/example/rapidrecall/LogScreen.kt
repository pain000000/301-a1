package com.example.rapidrecall

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlin.collections.joinToString

/**
 * Stores the necessary logic for what is displayed on the log screen
 *
 * Each Attempt objects which where stored in a list in RapidScreenController.kt
 * their stored information is dissected and wrapped into Column which is then
 * displayed in a LazyColumn.
 *
 * @param mainScreen: Lambda function which navigates back to the main screen
 * @param modifier: Modifier
 * @param attemptList: The list which stores all the recorded attempts in a session
 */
@Composable
fun LogScreen(
    mainScreen: () -> Unit,
    modifier: Modifier = Modifier,
    attemptList: MutableList<Attempt>
) {


    Column(
        modifier = modifier
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "LOG",
                fontSize = 90.sp,
                fontWeight = FontWeight.Bold,
                color = DMAUVE
            )
            Spacer(modifier = Modifier.height(30.dp))
            Text(
                text = "History Of All Your Attempts This Session Below",
                fontSize = 30.sp,
                color = PURPLE,
                lineHeight = 40.sp,
                textAlign = TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.height(50.dp))

        LazyColumn(
            modifier = Modifier.height(450.dp).fillMaxWidth(),
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

/**
 * Formats and displays the information stored in an Attempt object
 *
 * @param attempt: The attempt to be formatted
 * @param index: The attempt number
 */
@Composable
fun FormatAttempt(attempt: Attempt, index: Int) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
            .padding(16.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(if (attempt.check == "Correct!") RINDIGO else IBLUE)
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "ATTEMPT ${index+1}",
            color = Color.White, fontWeight = FontWeight.Bold,
            fontSize = 30.sp
        )
        Spacer(modifier = Modifier.height(10.dp))

        Text(text = "Sequence Length: ${(attempt.seqLength)}", color = Color.White)
        Text(text = "Generated Sequence: ${(attempt.sequence).joinToString(separator = "")}", color = Color.White)
        Text(text = "Your Guess: ${(attempt.input).joinToString(separator = "")}", color = Color.White)
        Text(text = "Result: ${attempt.check}", color = Color.White)
        Text(text = "Timestamp: ${attempt.timestamp}", color = Color.White)
        Spacer(modifier = Modifier.height(16.dp))
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