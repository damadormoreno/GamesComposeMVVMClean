package com.example.rawgcompose.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import coil.annotation.ExperimentalCoilApi
import com.example.rawgcompose.features.games.game_detail.GameDetailScreen
import com.example.rawgcompose.features.games.game_list.GamesScreen
import com.example.rawgcompose.features.games.game_search.GameSearchScreen
import com.example.rawgcompose.features.platform.PlatformScreen

@ExperimentalCoilApi
@Composable
fun NavigationHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.HomeScreen.route
    ) {
        composable(route = Screen.HomeScreen.route) { GamesScreen(navController = navController) }
        composable(route = Screen.GameDetailScreen.route + "/{gameId}") { GameDetailScreen() }
        composable(route = Screen.PlatformScreen.route) { PlatformScreen() }
        composable(route = Screen.GameSearchScreen.route) { GameSearchScreen(navController = navController) }

    }
}