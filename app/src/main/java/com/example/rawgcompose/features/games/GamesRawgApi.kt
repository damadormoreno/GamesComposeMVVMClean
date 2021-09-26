package com.example.rawgcompose.features.games

import com.example.rawgcompose.features.games.models.GameDetailDto
import com.example.rawgcompose.features.games.models.GameSearchDto
import com.example.rawgcompose.features.games.models.GamesDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface GamesRawgApi {

    @GET("games")
    fun getGames(@Query("key") key: String): Call<GamesDto>

    @GET
    fun getMoreGames(@Url url: String): Call<GamesDto>

    @GET("games?parent_platforms=1,2,3&search_precise=false&search_exact=false")
    fun searchGames(
        @Query("key") key: String,
        @Query("search") query: String,
    ): Call<GameSearchDto>

    @GET("games/{id}")
    fun getGameDetailById(
        @Path("id") gameId: Int,
        @Query("key") key: String
    ): Call<GameDetailDto>
}