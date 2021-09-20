package com.example.rawgcompose.features.games.game_search.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.rawgcompose.features.games.models.GameSearch

@Composable
fun SearchResults(results: List<GameSearch>, onSearchResultClicked: (Int) -> Unit) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        items(results.size) { index ->
            SearchItem(
                searchResult = results[index],
                onSearchResultClicked = onSearchResultClicked
            )
        }
    }
}