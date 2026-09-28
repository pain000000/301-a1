package com.example.madrona_rapidrecall

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Custom Color objects for the color palette of the app
val QLILAC = Color(0xffD391FA)
val DMAUVE = Color(0xffC364FA)
val PURPLE = Color(0xffA230ED)
val RINDIGO = Color(0xff6B00D7)
val IBLUE = Color(0xff3E00B3)
val RNAVY = Color(0xff190087)

// This enum class stores different route names for the screen
enum class RapidScreen() {
    MainMenu,
    Game,
    Log,
    Summary
}

/**
 * A Basic template for a button used in the app
 *
 * @param cmd: The lambda function when the Button is clicked
 * @param c1: The color of the button
 * @param text: The text displayed on the Button
 * @param width: The width of the Button
 * @param fontSize: The size of the text displayed on the Button
 */
@Composable
fun MenuButton(
    cmd: () -> Unit,
    c1: Color,
    text: String,
    width: Int,
    fontSize: Int
) {
    Button(
        onClick = cmd,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            contentColor = Color.White
        ),
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier.width(width.dp)
            .height(60.dp)
            .background(color = c1, shape = RoundedCornerShape(8.dp)),

        ) {
        Text(
            text = text,
            fontSize = fontSize.sp
        )
    }
}

/**
 *
 * NOTE:
 *      THE WALL OF TEXT BELOW IS ALL THE SOURCES AND REFERENCES I HAVE USED DURING
 *      THE MAKING OF THIS APPLICATION. THIS USES THE README.md TEMPLATE PROVIDED
 *      DURING LAB SESSIONS.
 *
 * GITHUB REPO LINK OF THE ASSIGNMENT:
 *      https://github.com/pain000000/301-a1.git
 *
 * # 301-a1
 * # CMPUT 301: Assignment 1
 *
 * ## Student Details
 * - **Full Name:** `Christian Madrona`
 * - **CCID:** `1800589`
 *
 * ## References and Resources
 * List any resources used here, or simply put `N/A` if not applicable.
 *
 * `README.md template copied from the lab-01 repo README.md`
 *
 * `"https://developer.android.com/codelabs/basic-android-kotlin-compose-navigation#0" tutorial was used as a heavy inspiration in how to set up and navigate different screens of the app`
 *
 * `"https://stackoverflow.com/questions/67401294/jetpack-compose-close-application-by-button" was used as a resource in order how to implement an exit method for the app. 22 Sept. 2026`
 *
 * `"https://www.schemecolor.com/blue-violet-gradient.php" was used as a source for the color palette`
 *
 * `"In Jetpack Compose, how do you use slideIntoContainer and fadeIn for a NavHost transition?" prompt. Gemini, 3.6 Thinking, Google, 22 Sept. 2026, https://gemini.google.com/app/ae835598a9c9046c?hl=en-CA`
 *
 * `"How to generate a getter function in a kotlin class" prompt. Gemini, 3.6 Thinking, Google, 22 Sept. 2026, https://gemini.google.com/app/ae835598a9c9046c?hl=en-CA`
 *
 * `"How to safely convert a string of integers into a list of Int in kotlin" prompt. Gemini, 3.6 Thinking, Google, 22 Sept. 2026, https://gemini.google.com/app/ae835598a9c9046c?hl=en-CA`
 *
 * `"How to generate a random list of integers between 1 and 9 in kotlin?" prompt. Gemini, 3.6 Thinking, Google, 22 Sept. 2026, https://gemini.google.com/app/ae835598a9c9046c?hl=en-CA`
 *
 * `"In Jetpack compose, how do you use LaunchedEffect to display a 3s countdown" prompt. Gemini, 3.6 Thinking, Google, 24 Sept. 2026, https://gemini.google.com/app/ae835598a9c9046c?hl=en-CA`
 *
 * `"How do you use LaunchedEffect in Jetpack compose to display all number inside a List<Int> and also using forEach" prompt. Gemini, 3.6 Thinking, Google, 24 Sept. 2026, https://gemini.google.com/app/ae835598a9c9046c?hl=en-CA`
 *
 * `"How do you generate a timestamp with the format "dd MMMM yyyy HH:mm:ss" in Kotlin?" prompt. Gemini, 3.6 Thinking, Google, 24 Sept. 2026, https://gemini.google.com/app/ae835598a9c9046c?hl=en-CA`
 *
 *
 * ## Verbal Collaboration
 * List students' names and CCIDs here, or simply put `N/A` if not applicable.
 *
 * `N/A`
 */