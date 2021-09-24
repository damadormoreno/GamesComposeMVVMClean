package com.example.rawgcompose.features.games.game_search

import com.example.rawgcompose.core.common.Resource
import com.example.rawgcompose.core.exception.Failure
import com.example.rawgcompose.features.games.GamesRepository
import com.example.rawgcompose.features.games.models.GameSearch
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchGamesUseCase @Inject constructor(
    private val repository: GamesRepository
) {
    operator fun invoke(query: String): Flow<Resource<Failure, List<GameSearch>>> = flow {
        emit(Resource.Loading())
        emit(repository.searchGames(query))
    }
}