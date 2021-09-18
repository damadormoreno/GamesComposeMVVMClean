package com.example.rawgcompose.features.games

import com.example.rawgcompose.features.games.models.GameDetailDto
import com.example.rawgcompose.features.games.models.GamesDto
import retrofit2.Retrofit
import javax.inject.Inject

class GamesService @Inject constructor(
    retrofit: Retrofit
) : GamesRawgApi {

    private val gamesApi by lazy { retrofit.create(GamesRawgApi::class.java) }

    override suspend fun getGames(key: String, page: Int): GamesDto = gamesApi.getGames(key, page)

    override suspend fun getGameDetailById(gameId: Int, key: String) : GameDetailDto =
        gamesApi.getGameDetailById(gameId, key)

}