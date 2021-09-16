package com.example.rawgcompose.features.games

import com.example.rawgcompose.core.common.Resource
import com.example.rawgcompose.features.games.models.GameDetail
import com.example.rawgcompose.features.games.models.Games
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