package com.example.rawgcompose.core.navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import coil.annotation.ExperimentalCoilApi
import com.example.rawgcompose.features.games.GameDetailScreen
import com.example.rawgcompose.features.games.GamesScreen
import com.example.rawgcompose.features.platform.PlatformScreen

@ExperimentalCoilApi
@ExperimentalFoundationApi
@Composable
fun NavigationHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.HomeScreen.route
    ) {
        composable(route = Screen.HomeScreen.route) { GamesScreen(navController = navController) }
        composable(route = Screen.GameDetailScreen.route + "/{gameId}") { GameDetailScreen() }
        composable(route = Screen.PlatformScreen.route) { PlatformScreen() }

    }
}