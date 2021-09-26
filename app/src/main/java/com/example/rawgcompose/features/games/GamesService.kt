package com.example.rawgcompose.features.games

import com.example.rawgcompose.features.games.models.GameDetailDto
import com.example.rawgcompose.features.games.models.GameSearchDto
import com.example.rawgcompose.features.games.models.GamesDto
import retrofit2.Call
import retrofit2.Retrofit
import javax.inject.Inject

class GamesService @Inject constructor(
    retrofit: Retrofit
) : GamesRawgApi {

    private val gamesApi by lazy { retrofit.create(GamesRawgApi::class.java) }

    override fun getGames(key: String): Call<GamesDto> = gamesApi.getGames(key)
    override suspend fun getMoreGames(url: String): GamesDto = gamesApi.getMoreGames(url)
    override suspend fun searchGames(key: String, query: String): GameSearchDto = gamesApi.searchGames(key, query)

    override suspend fun getGameDetailById(gameId: Int, key: String) : GameDetailDto =
        gamesApi.getGameDetailById(gameId, key)

}