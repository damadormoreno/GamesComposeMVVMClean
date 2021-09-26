package com.example.rawgcompose.features.games.game_search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rawgcompose.core.common.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class GameSearchViewModel @Inject constructor(
    private val searchGamesUseCase: SearchGamesUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(GameSearchState())
    val uiState: StateFlow<GameSearchState> = _uiState

    fun searchGames(query: String) {
        searchGamesUseCase(query = query).onEach {
            when(it) {
                is Resource.Loading -> _uiState.value = GameSearchState(isLoading = true)
                is Resource.Error -> _uiState.value = GameSearchState(error = "Error")
                is Resource.Success -> _uiState.value = GameSearchState(games = it.data?: emptyList())
            }
        }.launchIn(viewModelScope)
    }
}