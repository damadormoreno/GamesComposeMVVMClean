package com.example.rawgcompose.features.games

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.rawgcompose.core.common.Keys
import com.example.rawgcompose.core.platform.NetworkHandler
import com.example.rawgcompose.features.games.models.Games
import com.example.rawgcompose.features.games.models.toGames
import retrofit2.HttpException
import javax.inject.Inject

class GamesPagingSource @Inject constructor(
    private val networkHandler: NetworkHandler,
    private val service: GamesService,
) : PagingSource<Int, Games.Game>() {

    override fun getRefreshKey(state: PagingState<Int, Games.Game>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Games.Game> {

        when (networkHandler.isNetworkAvailable()) {
            true -> return try {
                val nextPage = params.key ?: 1
                val games = service.getGames(Keys.API_KEY, nextPage).toGames()
                LoadResult.Page(
                    data = games.games,
                    prevKey = if (nextPage == 1) null else nextPage - 1,
                    nextKey = if (games.games.isEmpty()) null else {
                        games.next.split("page=", limit = 0)[1].toInt() + 1
                    }
                )
            } catch (exception: HttpException) {
                return LoadResult.Error(exception)
            }

            false -> return LoadResult.Error(Throwable("No network"))
        }
    }


}