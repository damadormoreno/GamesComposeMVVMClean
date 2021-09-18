package com.example.rawgcompose.features.games

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.rawgcompose.core.common.Keys
import com.example.rawgcompose.core.common.Resource
import com.example.rawgcompose.core.platform.NetworkHandler
import com.example.rawgcompose.features.games.models.GameDetail
import com.example.rawgcompose.features.games.models.Games
import com.example.rawgcompose.features.games.models.toGameDetail
import com.example.rawgcompose.features.games.models.toGames
import kotlinx.coroutines.flow.Flow
import retrofit2.HttpException
import javax.inject.Inject

interface GamesRepository {

    fun getGames(): Flow<PagingData<Games.Game>>
    suspend fun getGameById(gameId: Int): Resource<GameDetail>

    class GamesRepositoryImpl @Inject constructor(
        private val service: GamesService,
        private val networkHandler: NetworkHandler
    ) : GamesRepository {

        override fun getGames(): Flow<PagingData<Games.Game>> {
            return Pager(
                PagingConfig(pageSize = 40, enablePlaceholders = false)
            ){
                GamesPagingSource(networkHandler, service)
            }.flow
        }


        override suspend fun getGameById(gameId: Int): Resource<GameDetail> {
            return when (networkHandler.isNetworkAvailable()) {
                true -> return try{
                    Resource.Success(service.getGameDetailById(gameId, Keys.API_KEY).toGameDetail())
                } catch (e: HttpException) {
                    Resource.Error(e.localizedMessage ?: "Server Error")
                }

                false -> Resource.Error("No network Avaliable")
            }
        }

    }
}