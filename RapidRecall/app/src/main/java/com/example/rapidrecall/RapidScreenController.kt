package com.example.rapidrecall

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.ActivityNavigator
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController








// Where the navigation of the screen displayed is handled via NavHost
@Composable
fun RapidApp(
    navController: NavHostController = rememberNavController(),
) {

    val attemptList = mutableListOf<Attempt>()

    // Controls the navigation between different screen displays
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