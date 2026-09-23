package com.example.rapidrecall

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






enum class RapidScreen() {
    MainMenu,
    Game,
    Log,
    Summary
}


// Where the navigation of the screen displayed is handled
@Composable
fun RapidApp(
    navController: NavHostController = rememberNavController()
) {


    NavHost(
        navController = navController,
        startDestination = RapidScreen.MainMenu.name
    ) {
        composable(route = RapidScreen.MainMenu.name) {
            MainMenu(
                logScreen = { navController.navigate(RapidScreen.Log.name) },
                gameScreen = { navController.navigate(RapidScreen.Game.name) },
                summaryScreen = { navController.navigate(RapidScreen.Summary.name) },
                exitScreen = {},
                modifier = Modifier.fillMaxSize()
            )
        }

        composable(route = RapidScreen.Log.name) {
            LogScreen(
                mainScreen = { navController.navigate(RapidScreen.MainMenu.name) },
                modifier = Modifier.fillMaxSize()
            )
        }

        composable(route = RapidScreen.Summary.name) {
            SummaryScreen(
                mainScreen = { navController.navigate(RapidScreen.MainMenu.name) },
                modifier = Modifier.fillMaxSize()
            )
        }

        composable(route = RapidScreen.Game.name) {
            GameScreen(
                mainScreen = { navController.navigate(RapidScreen.MainMenu.name) },
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}