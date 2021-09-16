package com.example.rawgcompose.features.games

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rawgcompose.core.common.Resource
import com.example.rawgcompose.features.games.models.GameDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameDetailViewModel @Inject constructor(
    private val getGameByIdUseCase: GetGameByIdUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(GameDetailState())
    val state: State<GameDetailState> = _state

    init {
        savedStateHandle.get<String>("gameId")?.let { gameId ->
            getGameDetail(gameId.toInt())
        }
    }

    private fun getGameDetail(gameId: Int) {

        getGameByIdUseCase(gameId).onEach {
            when (it) {
                is Resource.Loading -> _state.value = GameDetailState(isLoading = true)
                is Resource.Error -> _state.value = GameDetailState(error = it.message ?: "Error")
                is Resource.Success -> _state.value = GameDetailState(game = it.data)
            }
        }.launchIn(viewModelScope)

    }
}