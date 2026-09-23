package com.example.rapidrecall

import android.graphics.Paint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay


@Composable
fun GameScreen(
    mainScreen: () -> Unit,
    modifier: Modifier = Modifier
) {

    // these variables control what state of the game the user is currently in
    var onSequence by remember { mutableStateOf(false) }
    var onGuess by remember { mutableStateOf(false) }
    var onLength by remember { mutableStateOf(true) }

    // the actual variable that generates the random sequence
    val recall = Recall()

    // these variables hold the two inputs (length and guess) needed from the user
    var length by remember { mutableStateOf("") }
    var input by remember { mutableStateOf("") }

    var currLength: Int? = null
    val currGuess: String = ""

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
                                onLength = !onLength
                                onSequence = !onSequence
                            }
                        }
                    }
                ) {
                    Text("Enter")
                }
            }
        }

        //TODO: Add some sort of delay here (maybe a countdown)

        if (onSequence) {

            //Generate sequence with the provided length; store in sequence
            recall.generateRecall(currLength!!)




            Text("Im Displaying the sequence now")
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
        modifier = Modifier.fillMaxSize()
    )
}