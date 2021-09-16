package com.example.rawgcompose.features.games

import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel


@Composable
fun GameDetailScreen(viewModel: GameDetailViewModel = hiltViewModel()) {
    val state = viewModel.state.value
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = state.game?.name ?: "", textAlign = TextAlign.Center, modifier = Modifier.align(
                CenterHorizontally
            )
        )

        if (state.error.isNotBlank()) {
            Text(
                text = state.error,
                color = MaterialTheme.colors.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(CenterHorizontally)
            )
        }

        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(CenterHorizontally))
        }

    }

}