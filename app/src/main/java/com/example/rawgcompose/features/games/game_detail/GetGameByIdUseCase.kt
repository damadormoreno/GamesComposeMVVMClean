package com.example.rawgcompose.features.games.game_detail

import com.example.rawgcompose.core.common.Resource
import com.example.rawgcompose.core.exception.Failure
import com.example.rawgcompose.core.interactor.FlowUseCase
import com.example.rawgcompose.features.games.GamesRepository
import com.example.rawgcompose.features.games.models.GameDetail
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetGameByIdUseCase @Inject constructor(
    private val repository: GamesRepository
) : FlowUseCase<Params, GameDetail>(Dispatchers.IO) {

    override fun execute(parameters: Params): Flow<Resource<Failure, GameDetail>> {
        return repository.getGameById(parameters.gameId)
    }
}
data class Params(val gameId: Int)