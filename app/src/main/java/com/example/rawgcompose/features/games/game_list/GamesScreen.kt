package com.example.rawgcompose.features.games.game_list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import com.example.rawgcompose.R
import com.example.rawgcompose.core.navigation.Screen
import com.example.rawgcompose.features.games.game_list.components.GameCardItem
import com.example.rawgcompose.features.games.game_list.components.HomeAppBar


@ExperimentalCoilApi
@Composable
fun GamesScreen(navController: NavController, viewModel: GamesViewModel = hiltViewModel()) {

    val uiState by viewModel.uiState.collectAsState()
    val state = rememberLazyListState()

    Box(modifier = Modifier.fillMaxSize()) {
        Column {
            HomeAppBar(
                title = stringResource(id = R.string.app_name),
                searchClick = { navController.navigate(Screen.GameSearchScreen.route) },
                filterClick = { }
            )
            LazyColumn(
                state = state,
                contentPadding = PaddingValues(
                    start = 12.dp,
                    top = 16.dp,
                    end = 12.dp,
                    bottom = 16.dp
                ),
                content = {
                    items(uiState.games.size) { index ->

                        if (uiState.games.lastIndex == index) {
                            viewModel.getMoreGames()
                        }

                        GameCardItem(game = uiState.games[index]) {
                            navController.navigate(Screen.GameDetailScreen.route + "/${it.id}")
                        }
                    }
                }
            )

        }
        if (uiState.error.isNotBlank()) {
            Text(
                text = uiState.error,
                color = MaterialTheme.colors.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Center)
            )
        }

        if (uiState.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }

}