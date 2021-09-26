package com.example.rawgcompose.features.games.game_list

import com.example.rawgcompose.core.common.Resource
import com.example.rawgcompose.core.exception.Failure
import com.example.rawgcompose.core.interactor.FlowUseCase
import com.example.rawgcompose.core.interactor.None
import com.example.rawgcompose.features.games.GamesRepository
import com.example.rawgcompose.features.games.models.Games
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetGamesUseCase @Inject constructor(
    private val repository: GamesRepository
) : FlowUseCase<None, Games>(Dispatchers.IO){
    override fun execute(parameters: None): Flow<Resource<Failure, Games>> {
        return repository.getGames()
    }
}