package com.example.rawgcompose.features.games.models

data class Games(
    val next: String = "",
    val previous: Any? = Any(),
    val games: List<Game> = emptyList()
) {
    data class Game(
        val id: Int = 0,
        val name: String = "",
        val backgroundImage: String = "",
    )
}
