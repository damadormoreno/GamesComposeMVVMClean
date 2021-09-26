package com.example.rawgcompose.features.games.game_search

import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.rawgcompose.core.navigation.Screen
import com.example.rawgcompose.features.games.game_search.components.SearchBar
import com.example.rawgcompose.features.games.game_search.components.SearchResults

@Composable
fun GameSearchScreen(
    gameSearchViewModel: GameSearchViewModel = hiltViewModel(),
    navController: NavHostController
) {
    //val keyboardController = LocalSoftwareKeyboardController.current
    val uiState by gameSearchViewModel.uiState.collectAsState()
    val focusManager = LocalFocusManager.current

    DisposableEffect(true) {
        onDispose {
            //keyboardController?.hide()
            focusManager.clearFocus()
        }
    }

    fun onSearch(query: String) {
        gameSearchViewModel.searchGames(query)
    }

    Column(modifier = Modifier.fillMaxSize()) {
        SearchBar(
            focusManager = focusManager,
            onSearch = { query -> onSearch(query) },
            navHostController = navController
        )

        if (uiState.games.isNotEmpty()) {
            val results = uiState.games
            SearchResults(
                results = results,
                onSearchResultClicked = { id -> navController.navigate(Screen.GameDetailScreen.route + "/${id}") }
            )
        }

        if (uiState.error.isNotBlank()) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = uiState.error,
                    color = MaterialTheme.colors.error,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                )
            }
        }

        if (uiState.isLoading) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator()
            }
        }
    }
}