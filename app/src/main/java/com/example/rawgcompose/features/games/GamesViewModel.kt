package com.example.rawgcompose.features.games

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
class GamesViewModel @Inject constructor(
    private val getGamesUseCase: GetGamesUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(GamesState())
    val uiState: StateFlow<GamesState> = _uiState

    init {
        getGames()
    }

    private fun getGames() {
        getGamesUseCase().onEach {
            when (it) {
                is Resource.Loading -> _uiState.value = GamesState(isLoading = true)
                is Resource.Error -> _uiState.value = GamesState(error = it.message ?: "Error")
                is Resource.Success -> _uiState.value = GamesState(games = it.data!!.games)
            }
        }.launchIn(viewModelScope)
    }

}