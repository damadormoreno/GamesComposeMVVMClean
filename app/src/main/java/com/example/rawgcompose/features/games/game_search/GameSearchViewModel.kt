package com.example.rawgcompose.features.games.game_search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rawgcompose.core.common.Resource
import com.example.rawgcompose.core.exception.Failure
import com.example.rawgcompose.features.games.game_list.GamesState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class GameSearchViewModel @Inject constructor(
    private val searchGamesUseCase: SearchGamesUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(GameSearchState())
    val uiState: StateFlow<GameSearchState> = _uiState

    fun searchGames(query: String) {
        searchGamesUseCase(Params(query))
            .onStart { _uiState.value = GameSearchState(isLoading = true) }
            .onEach {
            when(it) {
                is Resource.Loading -> _uiState.value = GameSearchState(isLoading = true)
                is Resource.Error -> handleFailure(it.error)
                is Resource.Success -> _uiState.value = GameSearchState(games = it.data?: emptyList())
            }
        }.launchIn(viewModelScope)
    }

    private fun handleFailure(failure: Failure?) {
        val message = when (failure) {
            is Failure.NetworkConnection -> failure.errorMessage
            is Failure.ServerError -> failure.errorMessage
            is Failure.CustomError -> failure.errorMessage
            else -> "Unknow Error"
        }
        _uiState.value = GameSearchState(error = message)

    }
}