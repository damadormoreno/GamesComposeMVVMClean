package com.example.rawgcompose.features.games

import com.example.rawgcompose.features.games.models.GameDetailDto
import com.example.rawgcompose.features.games.models.GamesDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GamesRawgApi {

    @GET("games")
    suspend fun getGames(@Query("key") key: String, @Query("page") page: Int): GamesDto

    @GET("games/{id}")
    suspend fun getGameDetailById(
        @Path("id") gameId: Int,
        @Query("key") key: String
    ): GameDetailDto
}