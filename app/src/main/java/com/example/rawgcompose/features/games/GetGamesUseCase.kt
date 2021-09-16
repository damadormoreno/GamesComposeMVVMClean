package com.example.rawgcompose.features.games

import com.example.rawgcompose.core.common.Resource
import com.example.rawgcompose.features.games.models.Games
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetGamesUseCase @Inject constructor(
    private val repository: GamesRepository
) {
    operator fun invoke(): Flow<Resource<Games>> = flow {
        emit(Resource.Loading())
        emit(repository.getGames())
    }
}