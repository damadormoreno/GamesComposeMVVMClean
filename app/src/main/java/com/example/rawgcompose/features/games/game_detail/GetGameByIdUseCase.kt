package com.example.rawgcompose.features.games.game_detail

import com.example.rawgcompose.core.common.Resource
import com.example.rawgcompose.features.games.GamesRepository
import com.example.rawgcompose.features.games.models.GameDetail
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetGameByIdUseCase @Inject constructor(
    private val repository: GamesRepository
) {
    operator fun invoke(gameId: Int): Flow<Resource<GameDetail>> = flow {
        emit(Resource.Loading())
        emit(repository.getGameById(gameId))
    }
}