package com.example.rapidrecall


import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.time.Duration.Companion.milliseconds

/**
 * Stores the necessary logic for the Rapid Recall game to work
 *
 * Each stage of the game is controlled via if statements and
 * navigation of each stage is handled by a Button click. At
 * the last of the game, a new object of class Attempt is generated
 * and stored inside a list instantiated in RapidScreenController.kt
 *
 * @param mainScreen: Lambda function which navigates back to the main screen
 * @param modifier: Modifier
 * @param addList: Lambda function which adds the Attempt object unto a list
 */
@Composable
fun GameScreen(
    mainScreen: () -> Unit,
    modifier: Modifier = Modifier,
    addList: (Attempt) -> Unit
) {

    // These variables control what state of the game the user is currently in
    var onLength by remember { mutableStateOf(true) }
    var onCountdown by remember { mutableStateOf(false) }
    var onDisplaySequence by remember { mutableStateOf(false) }
    var onGuess by remember { mutableStateOf(false) }
    var onResult by remember { mutableStateOf(false) }


    // This variable stores and generates the desired sequence of numbers
    val recall by remember { mutableStateOf<Recall>(Recall())}

    // These variables hold the two inputs (length and input) needed from the user
    var length by remember { mutableStateOf("") }
    var input by remember { mutableStateOf("") }

    // This variable stores the result of the guess
    var check by remember { mutableStateOf("")}

    // This variable stores the verified number for sequence length
    var currLength by remember { mutableStateOf<Int?>(null)}

    // This variable stores the displayed number's position in the sequence
    var seqCount by remember { mutableIntStateOf(0)}

    // This variable controls the countdown
    var count by remember { mutableIntStateOf(3) }

    // This variable stores the currently displayed number in the sequence
    var currSeqNum by remember { mutableStateOf<Int?>(null) }

    // This column stores the whole screen display
    Column(
        modifier = modifier
    ) {
        // Stage 1: Gather the user's preferred number sequence length
        if (onLength) {

            Spacer(modifier = Modifier.height(300.dp))

            Column(
                modifier = Modifier.wrapContentSize().padding(16.dp)
            ) {
                Text(
                    text = "Enter Your Preferred Sequence Length Below.",
                    fontSize = 30.sp,
                    textAlign = TextAlign.Center,
                    lineHeight = 40.sp,
                    color = DMAUVE
                )
            }

            Spacer(modifier = Modifier.height(100.dp))

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OutlinedTextField(
                    value = length,
                    textStyle = TextStyle(textAlign = TextAlign.Center, color = PURPLE),
                    onValueChange = { length = it },
                    label = { Text(text = "Sequence Length") },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = PURPLE,
                        unfocusedBorderColor = PURPLE,
                        focusedLabelColor = PURPLE,
                        unfocusedLabelColor = PURPLE
                    ),
                )

                Spacer(modifier = Modifier.height(10.dp))

                MenuButton(
                    c1 = RINDIGO,
                    width = 120,
                    fontSize = 20,
                    text = "ENTER",
                    cmd = {
                        currLength = length.toIntOrNull()
                        length = ""

                        // Check if given length is valid
                        if (currLength != null) {
                            if (currLength!! > 0) {

                                // Generate the random sequence
                                recall.generateRecall(currLength!!)
                                onLength = !onLength
                                onCountdown = !onCountdown
                            }
                        }
                    }
                )
            }
        }

        // Stage 2: Display a countdown
        if (onCountdown) {

            // Controls the countdown sequence
            LaunchedEffect( key1 = count) {
                if (count > -1) {
                    delay(1000L.milliseconds)
                    count -= 1
                }

                if (count == -1) {
                    onCountdown = !onCountdown
                    onDisplaySequence = !onDisplaySequence
                }
            }

            Spacer(modifier = Modifier.height(400.dp))

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Text(
                    text = if (count > 0) "$count" else "Go",
                    fontSize = 50.sp,
                    color = DMAUVE
                )
            }
        }

        // Stage 3: Display the generated number sequence
        if (onDisplaySequence) {

            // Displays the sequence of numbers
            LaunchedEffect(key1 = recall.sequence) {

                (recall.sequence).forEach { number ->
                    currSeqNum = number
                    seqCount += 1
                    delay(1000L.milliseconds)

                }
                currSeqNum = null
                seqCount = 0
                onDisplaySequence = !onDisplaySequence
                onGuess = !onGuess
            }

            Spacer(modifier = Modifier.height(325.dp))

            Column(
               modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = if (currSeqNum != null) "$currSeqNum" else "",
                    fontSize = 150.sp,
                    color = RINDIGO
                )
                Text(text = if (seqCount > 0) "Number $seqCount" else "",
                    fontSize = 15.sp,
                    color = RNAVY
                )
            }
        }

        // Stage 4: Ask for the user's guess
        if (onGuess) {
            Spacer(modifier = Modifier.height(300.dp))

            Column(
                modifier = Modifier.wrapContentSize().padding(16.dp)
            ) {
                Text(
                    text = "Enter Your Guess Of The Sequence Below",
                    fontSize = 30.sp,
                    textAlign = TextAlign.Center,
                    lineHeight = 40.sp,
                    color = DMAUVE
                )
            }

            Spacer(modifier = Modifier.height(100.dp))

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OutlinedTextField(
                    value = input,
                    textStyle = TextStyle(textAlign = TextAlign.Center, color = PURPLE),
                    onValueChange = { input = it },
                    label = { Text(text = "Enter Your Guess Here") },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = PURPLE,
                        unfocusedBorderColor = PURPLE,
                        focusedLabelColor = PURPLE,
                        unfocusedLabelColor = PURPLE
                    ),
                )
                Spacer(modifier = Modifier.height(10.dp))

                MenuButton(
                    c1 = RINDIGO,
                    width = 120,
                    fontSize = 20,
                    text = "GUESS",
                    cmd = {
                        check = recall.checkAnswer(input)
                        onResult = !onResult
                        onGuess = !onGuess
                    }
                )

            }
        }

        // Stage 5: Display the result of the game
        if (onResult) {

            // Convert the input into List<Int> and exclude not Int characters
            val inputList = input.mapNotNull { it.digitToIntOrNull() }

            Spacer(modifier = Modifier.height(300.dp))

            // Controls the generation of an Attempt class and adding it into the list
            LaunchedEffect(Unit) {
                val formatter = SimpleDateFormat("dd MMMM yyyy HH:mm:ss", Locale.ENGLISH)
                val timestamp = formatter.format(Date())

                val attempt = Attempt(
                    seqLength = currLength!!,
                    input = inputList,
                    sequence = recall.sequence,
                    timestamp = timestamp,
                    check = check
                )

                addList(attempt)
            }

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = check,
                    fontSize = 85.sp,
                    color = DMAUVE
                )

                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    modifier = Modifier.horizontalScroll(rememberScrollState())
                        .padding(16.dp)
                ) {
                    Text(
                        text ="Generated Sequence: ${(recall.sequence).joinToString(separator = "")}",
                        color = PURPLE
                    )
                }

                Row(
                    modifier = Modifier.horizontalScroll(rememberScrollState())
                        .padding(16.dp)
                ) {
                    Text(
                        text = "Your Guess: ${inputList.joinToString(separator = "")}",
                        color = PURPLE
                    )
                }

                Spacer(modifier = Modifier.height(30.dp))

                MenuButton(
                    c1 = RINDIGO,
                    width = 180,
                    fontSize = 20,
                    text = "TRY AGAIN",
                    cmd = {
                        // Reset the whole loop
                        input = ""
                        length = ""
                        check = ""
                        count = 3
                        currLength = null

                        onLength = !onLength
                        onResult = !onResult
                    }
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        // This column stores the BACK button to navigate back to the main screen
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
    fontScale = 1.3f,
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
