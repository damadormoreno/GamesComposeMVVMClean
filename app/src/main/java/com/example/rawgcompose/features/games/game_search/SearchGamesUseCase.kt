package com.example.rawgcompose.features.games.game_search

import com.example.rawgcompose.core.common.Resource
import com.example.rawgcompose.core.exception.Failure
import com.example.rawgcompose.core.interactor.FlowUseCase
import com.example.rawgcompose.features.games.GamesRepository
import com.example.rawgcompose.features.games.models.GameSearch
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchGamesUseCase @Inject constructor(
    private val repository: GamesRepository
) : FlowUseCase<Params, List<GameSearch>>(Dispatchers.IO) {
    override fun execute(parameters: Params): Flow<Resource<Failure, List<GameSearch>>> {
        return repository.searchGames(parameters.query)
    }
}

data class Params(val query: String)