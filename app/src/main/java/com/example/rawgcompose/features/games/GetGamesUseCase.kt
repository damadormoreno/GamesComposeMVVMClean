package com.example.rawgcompose.features.games

import androidx.paging.PagingData
import com.example.rawgcompose.features.games.models.Games
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetGamesUseCase @Inject constructor(
    private val repository: GamesRepository
) {
    operator fun invoke(): Flow<PagingData<Games.Game>> = repository.getGames()
}