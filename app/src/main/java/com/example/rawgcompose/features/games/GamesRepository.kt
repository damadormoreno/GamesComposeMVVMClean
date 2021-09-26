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
    fun searchGames(query: String): Flow<Resource<Failure, List<GameSearch>>>
    fun getMoreGames(url: String): Flow<Resource<Failure, Games>>
    fun getGameById(gameId: Int): Flow<Resource<Failure, GameDetail>>

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

        override fun searchGames(query: String): Flow<Resource<Failure, List<GameSearch>>> {
            return flow {
                when (networkHandler.isNetworkAvailable()) {
                    true -> emit(request(service.searchGames(key = Keys.API_KEY, query = query), {
                        it.toGameSearchList()
                    }, GameSearchDto()))
                    false -> emit(Resource.Error(Failure.NetworkConnection(errorMessage = "No Network")))
                }
            }
        }

        override fun getMoreGames(url: String): Flow<Resource<Failure, Games>> {

            return flow {
                when (networkHandler.isNetworkAvailable()) {
                    true -> emit(request(service.getMoreGames(url), {
                        it.toGames()
                    }, GamesDto()))
                    false -> emit(Resource.Error(Failure.NetworkConnection(errorMessage = "No Network")))
                }
            }
        }

        override fun getGameById(gameId: Int): Flow<Resource<Failure, GameDetail>> {
            return flow {
                when (networkHandler.isNetworkAvailable()) {
                    true -> emit(request(service.getGameDetailById(gameId, Keys.API_KEY), {
                        it.toGameDetail()
                    }, GameDetailDto()))
                    false -> emit(Resource.Error(Failure.NetworkConnection(errorMessage = "No Network")))
                }
            }
        }

    }
}