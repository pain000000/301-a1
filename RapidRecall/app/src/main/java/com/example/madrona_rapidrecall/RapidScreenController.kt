package com.example.madrona_rapidrecall

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

/**
 * Stores the necessary logic to control what is screen is displayed at any moment within the app
 *
 * Navigating between screen is made possible by NavHost which uses navController to control which
 * screen is displayed at any point in time. Each screen is stored inside a composable and accompanied
 * by a route name.
 *
 * @param navController: The "Controller" of which screen is displayed
 */
@Composable
fun RapidApp(
    navController: NavHostController = rememberNavController(),
) {
    // The list containing of all attempts recorded
    val attemptList = mutableListOf<Attempt>()

    // Sets up the navigation between screens and sets up which screen is displayed first
    NavHost(
        navController = navController,
        startDestination = RapidScreen.MainMenu.name,
        enterTransition = {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Start,
                animationSpec = tween(500)
            ) + fadeIn(animationSpec = tween(500))
        },
        exitTransition = {
            slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.End,
                animationSpec = tween(500)
            ) + fadeOut(animationSpec = tween(500))
        }
    ) {
        // Starting Screen
        composable(route = RapidScreen.MainMenu.name) {
            MainMenu(
                logScreen = { navController.navigate(RapidScreen.Log.name) },
                gameScreen = { navController.navigate(RapidScreen.Game.name) },
                summaryScreen = { navController.navigate(RapidScreen.Summary.name) },
                modifier = Modifier.fillMaxSize()
            )
        }
        // Log Screen
        composable(route = RapidScreen.Log.name) {
            LogScreen(
                mainScreen = { navController.navigate(RapidScreen.MainMenu.name) },
                modifier = Modifier.fillMaxSize(),
                attemptList = attemptList
            )
        }
        // Summary Screen
        composable(route = RapidScreen.Summary.name) {
            SummaryScreen(
                mainScreen = { navController.navigate(RapidScreen.MainMenu.name) },
                modifier = Modifier.fillMaxSize(),
                attemptList = attemptList
            )
        }
        // Game Screen
        composable(route = RapidScreen.Game.name) {
            GameScreen(
                mainScreen = { navController.navigate(RapidScreen.MainMenu.name) },
                modifier = Modifier.fillMaxSize(),
                addList = { attemptList.add(it) }
            )
        }
    }
}