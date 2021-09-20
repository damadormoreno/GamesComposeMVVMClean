package com.example.rawgcompose.features.games

import com.example.rawgcompose.features.games.models.GameDetailDto
import com.example.rawgcompose.features.games.models.GamesDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface GamesRawgApi {

    @GET("games")
    suspend fun getGames(@Query("key") key: String): GamesDto

    @GET
    suspend fun getMoreGames(@Url url: String): GamesDto

    @GET("games/{id}")
    suspend fun getGameDetailById(
        @Path("id") gameId: Int,
        @Query("key") key: String
    ): GameDetailDto
}