package com.example.rawgcompose.features.games.game_detail

import androidx.lifecycle.SavedStateHandle
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
class GameDetailViewModel @Inject constructor(
    private val getGameByIdUseCase: GetGameByIdUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiState = MutableStateFlow(GameDetailState())
    val uiState: StateFlow<GameDetailState> = _uiState

    init {
        savedStateHandle.get<String>("gameId")?.let { gameId ->
            getGameDetail(gameId.toInt())
        }
    }

    private fun getGameDetail(gameId: Int) {

        getGameByIdUseCase(gameId).onEach {
            when (it) {
                is Resource.Loading -> _uiState.value = GameDetailState(isLoading = true)
                is Resource.Error -> _uiState.value = GameDetailState(error = it.message ?: "Error")
                is Resource.Success -> _uiState.value = GameDetailState(game = it.data)
            }
        }.launchIn(viewModelScope)

    }
}