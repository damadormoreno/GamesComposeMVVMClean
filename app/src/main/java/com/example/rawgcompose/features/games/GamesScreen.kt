package com.example.rawgcompose.features.games

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.annotation.ExperimentalCoilApi
import com.example.rawgcompose.core.navigation.Screen
import com.example.rawgcompose.features.games.components.GameCardItem
import com.example.rawgcompose.features.games.models.Games

@ExperimentalFoundationApi
@ExperimentalCoilApi
@Composable
 fun GamesScreen(navController: NavController, viewModel: GamesViewModel = hiltViewModel()) {

    val gamesItems: LazyPagingItems<Games.Game> = viewModel.getGames().collectAsLazyPagingItems()
    val listState = rememberLazyListState()

        LazyVerticalGrid(
            state = listState,
            cells = GridCells.Fixed(2),

            // content padding
            contentPadding = PaddingValues(
                start = 12.dp,
                top = 16.dp,
                end = 12.dp,
                bottom = 16.dp
            ),
            content = {

                gamesItems.apply {

                    items(gamesItems.itemCount) { index ->
                        GameCardItem(game = gamesItems[index] ?: Games.Game()) {
                            navController.navigate(Screen.GameDetailScreen.route + "/${it.id}")
                        }
                    }
                    when {
                        loadState.refresh is LoadState.Loading -> {
                            //You can add modifier to manage load state when first time response page is loading
                            item { CircularProgressIndicator(modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp)
                                .wrapContentWidth(Alignment.CenterHorizontally)) }
                        }
                        loadState.append is LoadState.Loading -> {
                            //You can add modifier to manage load state when next response page is loading
                        }
                        loadState.append is LoadState.Error -> {
                            //You can use modifier to show error message
                            val e = gamesItems.loadState.append as LoadState.Error

                            item {
                                Text(
                                    text = e.error.localizedMessage?: "Error",
                                    color = MaterialTheme.colors.error,
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 20.dp)
                                )
                            }
                        }
                    }
                }
            }
        )


}