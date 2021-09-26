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
    override fun getMoreGames(url: String): Call<GamesDto> = gamesApi.getMoreGames(url)
    override fun searchGames(key: String, query: String): Call<GameSearchDto> =
        gamesApi.searchGames(key, query)

    override fun getGameDetailById(gameId: Int, key: String): Call<GameDetailDto> =
        gamesApi.getGameDetailById(gameId, key)

}