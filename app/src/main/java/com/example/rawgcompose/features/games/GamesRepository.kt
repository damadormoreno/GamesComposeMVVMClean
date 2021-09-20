package com.example.rawgcompose.features.games

import com.example.rawgcompose.core.common.Constants
import com.example.rawgcompose.core.common.Keys
import com.example.rawgcompose.core.common.Resource
import com.example.rawgcompose.core.platform.NetworkHandler
import com.example.rawgcompose.core.platform.request
import com.example.rawgcompose.features.games.models.*
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

interface GamesRepository {

    suspend fun getGames(): Resource<Games>
    suspend fun searchGames(query: String): Resource<List<GameSearch>>
    suspend fun getMoreGames(url: String): Resource<Games>
    suspend fun getGameById(gameId: Int): Resource<GameDetail>

    class GamesRepositoryImpl @Inject constructor(
        private val service: GamesService,
        private val networkHandler: NetworkHandler
    ) : GamesRepository {
        override suspend fun getGames(): Resource<Games> {
            return when (networkHandler.isNetworkAvailable()) {
                true -> return try {
                    val games = (service.getGames(Keys.API_KEY))
                    Resource.Success(games.toGames())
                } catch (e: HttpException) {
                    Resource.Error(e.localizedMessage ?: "Server Error")
                }
                false -> Resource.Error("No network Avaliable")
            }
        }

        override suspend fun searchGames(query: String): Resource<List<GameSearch>> {
            return when (networkHandler.isNetworkAvailable()) {
                true -> return try {
                    Resource.Success(
                        service.searchGames(key = Keys.API_KEY, query = query).toGameSearchList()
                    )
                } catch (e: HttpException) {
                    Resource.Error(e.localizedMessage ?: "Server Error")
                }
                false -> Resource.Error("No network Avaliable")
            }
        }

        override suspend fun getMoreGames(url: String): Resource<Games> {
            return when (networkHandler.isNetworkAvailable()) {
                true -> return try {
                    val games = (service.getMoreGames(url))
                    Resource.Success(games.toGames())
                } catch (e: HttpException) {
                    Resource.Error(e.localizedMessage ?: "Server Error")
                }
                false -> Resource.Error("No network Avaliable")
            }
        }

        override suspend fun getGameById(gameId: Int): Resource<GameDetail> {
            return when (networkHandler.isNetworkAvailable()) {
                true -> return try {
                    Resource.Success(service.getGameDetailById(gameId, Keys.API_KEY).toGameDetail())
                } catch (e: HttpException) {
                    Resource.Error(e.localizedMessage ?: "Server Error")
                }

                false -> Resource.Error("No network Avaliable")
            }
        }

    }
}