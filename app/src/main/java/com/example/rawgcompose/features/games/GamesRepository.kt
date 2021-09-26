package com.example.rawgcompose.features.games

import com.example.rawgcompose.core.common.Keys
import com.example.rawgcompose.core.common.Resource
import com.example.rawgcompose.core.exception.Failure
import com.example.rawgcompose.core.platform.NetworkHandler
import com.example.rawgcompose.core.platform.request
import com.example.rawgcompose.features.games.models.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject

interface GamesRepository {

    fun getGames(): Flow<Resource<Failure, Games>>
    suspend fun searchGames(query: String): Resource<Failure, List<GameSearch>>
    suspend fun getMoreGames(url: String): Resource<Failure, Games>
    suspend fun getGameById(gameId: Int): Resource<Failure, GameDetail>

    class GamesRepositoryImpl @Inject constructor(
        private val service: GamesService,
        private val networkHandler: NetworkHandler
    ) : GamesRepository {
        override fun getGames(): Flow<Resource<Failure, Games>> {
            return flow {
                when (networkHandler.isNetworkAvailable()) {
                    true -> emit(request(service.getGames(Keys.API_KEY), {
                        it.toGames()
                    }, GamesDto()))
                    false -> emit(Resource.Error(Failure.NetworkConnection(errorMessage = "No Network")))
                }
            }
        }

        override suspend fun searchGames(query: String): Resource<Failure, List<GameSearch>> {
            return when (networkHandler.isNetworkAvailable()) {
                true -> return try {
                    Resource.Success(
                        service.searchGames(key = Keys.API_KEY, query = query).toGameSearchList()
                    )
                } catch (e: HttpException) {
                    Resource.Error(Failure.ServerError(e.code(), e.message()))
                }
                false -> Resource.Error(Failure.NetworkConnection(errorMessage = "No Network"))
            }
        }

        override suspend fun getMoreGames(url: String): Resource<Failure, Games> {
            return when (networkHandler.isNetworkAvailable()) {
                true -> return try {
                    val games = (service.getMoreGames(url))
                    Resource.Success(games.toGames())
                } catch (e: HttpException) {
                    Resource.Error(Failure.ServerError(e.code(), e.message()))
                }
                false -> Resource.Error(Failure.NetworkConnection(errorMessage = "No Network"))
            }
        }

        override suspend fun getGameById(gameId: Int): Resource<Failure, GameDetail> {
            return when (networkHandler.isNetworkAvailable()) {
                true -> return try {
                    Resource.Success(service.getGameDetailById(gameId, Keys.API_KEY).toGameDetail())
                } catch (e: HttpException) {
                    Resource.Error(Failure.ServerError(e.code(), e.message()))
                }

                false -> Resource.Error(Failure.NetworkConnection(errorMessage = "No Network"))
            }
        }

    }
}