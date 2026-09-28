package com.example.madrona_rapidrecall

import kotlin.random.Random

/**
 * Generates a sequence of numbers of a specified length
 *
 * This class is only instantiated once during the application's lifetime
 * inside GameScreen.kt. Because this sequence must not be changed each
 * time generateRecall() method is called, the variable that stores the
 * number sequence must be private and accessed through a getter method.
 */
class Recall {

    private var _sequence: List<Int> = emptyList()

    /**
     * Getter function for the private var _sequence
     */
    val sequence: List<Int>
        get() = _sequence

    /**
     * Generates the number sequence of the specified length
     *
     * @param length: The length of the number sequence
     */
    fun generateRecall(length: Int) {
        _sequence = List(length) { Random.nextInt(1, 10)}
    }

    /**
     * Compares the user's guess to the actual generated sequence
     *
     * @param guess: The user's guess
     * @return A String indicating if the user was 'Correct!' or 'Incorrect!'
     */
    fun checkAnswer(guess: String): String {
        val parsedGuess = guess.mapNotNull { it.digitToIntOrNull() }

        return if (parsedGuess == _sequence) {
            "Correct!"
        } else {
            "Incorrect!"
        }

    }

}

