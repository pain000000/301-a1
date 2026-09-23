package com.example.rapidrecall

import kotlin.random.Random






// Only one object of Recall per session;
// Attempt will store what is in each generated recall

class Recall {

    private var _sequence: List<Int> = emptyList()

    // modify
    val sequence: List<Int>
        get() = _sequence

    fun generateRecall(length: Int) {
        _sequence = List(length) { Random.nextInt(1, 10)}
    }

    fun checkAnswer(guess: String): String {
        val parsedGuess = guess.mapNotNull { it.digitToIntOrNull() }

        return if (parsedGuess == _sequence) {
            "Correct!"
        } else {
            "Incorrect!"
        }

    }

}

