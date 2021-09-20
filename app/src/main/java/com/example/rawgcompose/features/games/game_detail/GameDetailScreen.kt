package com.example.rawgcompose.features.games.game_detail

import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel


@Composable
fun GameDetailScreen(viewModel: GameDetailViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsState()
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = uiState.game?.name ?: "", textAlign = TextAlign.Center, modifier = Modifier.align(
                CenterHorizontally
            )
        )

        if (uiState.error.isNotBlank()) {
            Text(
                text = uiState.error,
                color = MaterialTheme.colors.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(CenterHorizontally)
            )
        }

        if (uiState.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(CenterHorizontally))
        }

    }

}