package com.example.rawgcompose.features.games.models

data class GameDetail(
    val id: Int = 0,
    val name: String = "",
    val nameOriginal: String = "",
    val description: String = "",
    val released: String = "",
    val backgroundImage: String = "",
    val backgroundImageAdditional: String = "",
    val website: String = "",
    val rating: Double = 0.0,
    val playtime: Int = 0,
    val tags: List<String> = emptyList(),
    val esrbRating: String = "",
)