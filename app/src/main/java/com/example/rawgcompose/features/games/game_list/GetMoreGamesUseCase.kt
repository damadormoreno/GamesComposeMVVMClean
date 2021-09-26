package com.example.rawgcompose.features.games.game_list

import com.example.rawgcompose.core.common.Resource
import com.example.rawgcompose.core.exception.Failure
import com.example.rawgcompose.core.interactor.FlowUseCase
import com.example.rawgcompose.features.games.GamesRepository
import com.example.rawgcompose.features.games.models.Games
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMoreGamesUseCase @Inject constructor(
    private val repository: GamesRepository
) : FlowUseCase<Params, Games>(Dispatchers.IO) {
    override fun execute(parameters: Params): Flow<Resource<Failure, Games>> {
        return repository.getMoreGames(parameters.url)
    }
}

data class Params(val url: String)