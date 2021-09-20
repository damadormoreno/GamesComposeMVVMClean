package com.example.rawgcompose.features.games.game_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rawgcompose.core.common.Resource
import com.example.rawgcompose.features.games.models.Games
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class GamesViewModel @Inject constructor(
    private val getGamesUseCase: GetGamesUseCase,
    private val getMoreGamesUseCase: GetMoreGamesUseCase
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
                is Resource.Success -> _uiState.value =
                    GamesState(games = it.data!!.games, next = it.data.next)
            }
        }.launchIn(viewModelScope)
    }

    fun getMoreGames() {
        getMoreGamesUseCase(_uiState.value.next).onEach {
            when (it) {
                is Resource.Error -> {
                    _uiState.value = GamesState(error = it.message ?: "Error")
                }
                is Resource.Loading -> {}
                is Resource.Success -> {
                    val list: MutableList<Games.Game> = _uiState.value.games.toMutableList()
                    val addedList: MutableList<Games.Game> =
                        it.data?.games?.toMutableList() ?: mutableListOf()
                    if (addedList.isNotEmpty()) list.addAll(addedList)
                    _uiState.value = GamesState(
                        games = list.toList(),
                        next = it.data?.next?: ""
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

}