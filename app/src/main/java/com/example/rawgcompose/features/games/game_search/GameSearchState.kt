package com.example.rawgcompose.features.games.game_search

import com.example.rawgcompose.features.games.models.GameSearch
import com.example.rawgcompose.features.games.models.Games

data class GameSearchState(
    val isLoading: Boolean = false,
    val games: List<GameSearch> = emptyList(),
    val error: String = ""
)