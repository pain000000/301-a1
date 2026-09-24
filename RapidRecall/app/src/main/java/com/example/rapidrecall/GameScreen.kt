package com.example.rapidrecall

import android.graphics.Paint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onVisibilityChangedNode
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.Date
import java.util.Locale
import kotlin.time.Duration.Companion.milliseconds


@Composable
fun GameScreen(
    mainScreen: () -> Unit,
    modifier: Modifier = Modifier,
    addList: (Attempt) -> Unit
) {

    // these variables control what state of the game the user is currently in
    var onLength by remember { mutableStateOf(true) }
    var onCountdown by remember { mutableStateOf(false) }
    var displaySequence by remember { mutableStateOf(false) }
    var onGuess by remember { mutableStateOf(false) }
    var onResult by remember { mutableStateOf(false) }


    // the actual variable that generates the random sequence
    val recall by remember { mutableStateOf<Recall>(Recall())}

    // these variables hold the two inputs (length and guess) needed from the user
    var length by remember { mutableStateOf("") }
    var input by remember { mutableStateOf("") }
    var check by remember { mutableStateOf("")}

    var currLength by remember { mutableStateOf<Int?>(null)}

    // controls the countdown
    var count by remember { mutableIntStateOf(3) }

    // stores the curr number in the sequence
    var currSeqNum by remember { mutableStateOf<Int?>(null) }




    Column(
        modifier = modifier
    ) {
        if (onLength) {
            Spacer(modifier = Modifier.height(300.dp))
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OutlinedTextField(
                    value = length,
                    onValueChange = { length = it },
                    label = { Text(text = "Enter Sequence Length") }
                )
                Spacer(modifier = Modifier.height(10.dp))
                Button(
                    onClick = {

                        currLength = length.toIntOrNull()
                        length = ""

                        // check if given length is valid
                        if (currLength != null) {
                            if (currLength!! > 0) {

                                // generate random sequence
                                recall.generateRecall(currLength!!)
                                onLength = !onLength
                                onCountdown = !onCountdown
                            }
                        }
                    }
                ) {
                    Text("Enter")
                }
            }
        }

        if (onCountdown) {

            // Controls the countdown
            LaunchedEffect( key1 = count) {
                if (count > -1) {
                    delay(1000L.milliseconds)
                    count -= 1
                }
            }

            Spacer(modifier = Modifier.height(300.dp))

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Text(
                    text = if (count > 0) "$count" else "Go",
                    fontSize = 50.sp
                )
            }

            if (count == -1) {
                displaySequence = !displaySequence
                onCountdown = !onCountdown

            }
        }

        if (displaySequence) {

            //TODO: ADD an indicator what place of the sequence it is!

            // Controls the displayed Sequence of numbers
            LaunchedEffect(key1 = recall.sequence) {

                (recall.sequence).forEach { number ->
                    currSeqNum = number
                    delay(1000L.milliseconds)

                }
                currSeqNum = null
                displaySequence = !displaySequence
                onGuess = !onGuess
            }

            Spacer(modifier = Modifier.height(300.dp))

            Column(
               modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = if (currSeqNum != null) "$currSeqNum" else "",
                    fontSize = 100.sp
                )
            }
        }

        if (onGuess) {
            Spacer(modifier = Modifier.height(300.dp))
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OutlinedTextField(
                    value = input,
                    onValueChange = { input = it },
                    label = { Text(text = "Enter Your Guess Here") }
                )
                Spacer(modifier = Modifier.height(10.dp))
                Button(
                    onClick = {
                        //TODO: INPUT ATTEMPT OBJECTS
                        // check the guess here
                        check = recall.checkAnswer(input)
                        onResult = !onResult
                        onGuess = !onGuess
                    }
                ) {
                    Text("Guess")
                }
            }
        }

        if (onResult) {




            Spacer(modifier = Modifier.height(300.dp))

            val inputList = input.mapNotNull { it.digitToIntOrNull() }

            LaunchedEffect(Unit) {
                val formatter = SimpleDateFormat("dd MMMM yyyy HH:mm:ss", Locale.ENGLISH)
                val timestamp = formatter.format(Date())

                val attempt = Attempt(
                    seqLength = currLength!!,
                    input = inputList,
                    sequence = recall.sequence,
                    timestamp = timestamp
                )

                // Add Timestamp to list
                addList(attempt)
            }

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Result: $check")

                Spacer(modifier = Modifier.height(10.dp))

                Text("Sequence: ${recall.sequence}")

                Spacer(modifier = Modifier.height(10.dp))

                Text("Your Guess: $inputList")

                Spacer(modifier = Modifier.height(30.dp))

                Button(
                    onClick = {
                        // Tally Attempt Here:

                        // Create Timestamp



                        // Reset the whole loop here
                        input = ""
                        length = ""
                        check = ""
                        count = 3
                        currLength = null

                        onLength = !onLength
                        onResult = !onResult
                    }
                ) {
                    Text("Try Again?")
                }
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
                Text("Back")
            }
        }



    }
}


@Preview(
    showBackground = true
)
@Composable
fun GameScreenPreview() {
    GameScreen(
        mainScreen = {},
        modifier = Modifier.fillMaxSize(),
        addList = {}
    )
}

/**
 *
 * SAMPLE CODE FOR MAKING A LAZY COLUMN
 * Spacer(modifier = Modifier.height(300.dp))
 *
 *             LazyColumn(
 *                 modifier = Modifier.height(200.dp).fillMaxWidth(),
 *                 horizontalAlignment = Alignment.CenterHorizontally
 *             ) {
 *                 items(items = recall.sequence) {
 *                     number -> Text("$number")
 *                 }
 *             }
 */