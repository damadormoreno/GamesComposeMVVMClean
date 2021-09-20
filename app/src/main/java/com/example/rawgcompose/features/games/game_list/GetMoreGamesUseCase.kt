package com.example.rawgcompose.features.games.game_list

import com.example.rawgcompose.core.common.Resource
import com.example.rawgcompose.features.games.GamesRepository
import com.example.rawgcompose.features.games.models.Games
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetMoreGamesUseCase @Inject constructor(
    private val repository: GamesRepository
) {
    operator fun invoke(url: String): Flow<Resource<Games>> = flow {
        emit(Resource.Loading())
        emit(repository.getMoreGames(url))
    }
}