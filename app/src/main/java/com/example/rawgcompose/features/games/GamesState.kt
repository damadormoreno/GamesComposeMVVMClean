package com.example.rawgcompose.features.games

import com.example.rawgcompose.features.games.models.Games

data class GamesState(
    val isLoading: Boolean = false,
    val games: List<Games.Game> = emptyList(),
    val next: String = "",
    val error: String = ""
)