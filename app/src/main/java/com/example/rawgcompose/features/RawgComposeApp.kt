package com.example.rawgcompose.features

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import coil.annotation.ExperimentalCoilApi
import com.example.rawgcompose.core.navigation.NavigationHost
import com.example.rawgcompose.core.navigation.Screen
import com.example.rawgcompose.core.navigation.components.BottomNavigationBar


@ExperimentalComposeUiApi
@ExperimentalFoundationApi
@ExperimentalCoilApi
@Composable
fun RawgComposeApp() {
    // A surface container using the 'background' color from the theme
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route?.substringBeforeLast("/")

    Scaffold(
        bottomBar = {
            if (currentRoute != Screen.GameDetailScreen.route &&
                currentRoute != Screen.GameSearchScreen.route
            ) {
                BottomNavigationBar(navController = navController)
            }
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            NavigationHost(navController = navController)
        }
    }
}