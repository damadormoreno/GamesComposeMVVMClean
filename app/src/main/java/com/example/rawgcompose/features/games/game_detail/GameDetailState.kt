package com.example.rawgcompose.features.games.game_detail

import com.example.rawgcompose.features.games.models.GameDetail

data class GameDetailState(
    val isLoading: Boolean = false,
    val game: GameDetail? = null,
    val error: String = ""
)