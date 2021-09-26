package com.example.rawgcompose.features.games.game_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rawgcompose.core.common.Resource
import com.example.rawgcompose.core.exception.Failure
import com.example.rawgcompose.features.games.game_list.GamesState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
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

        getGameByIdUseCase(Params(gameId))
            .onStart { _uiState.value = GameDetailState(isLoading = true) }
            .onEach {
            when (it) {
                is Resource.Success -> _uiState.value = GameDetailState(game = it.data)
                is Resource.Loading -> _uiState.value = GameDetailState(isLoading = true)
                is Resource.Error -> handleFailure(it.error)

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
        _uiState.value = GameDetailState(error = message)

    }
}