package com.example.rawgcompose.features.games.game_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rawgcompose.core.common.Resource
import com.example.rawgcompose.core.exception.Failure
import com.example.rawgcompose.core.interactor.None
import com.example.rawgcompose.features.games.models.Games
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
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
        getGamesUseCase(None())
            .onStart { _uiState.value = GamesState(isLoading = true) }
            .onEach {
                when (it) {
                    is Resource.Loading -> _uiState.value = GamesState(isLoading = true)
                    is Resource.Error -> handleFailure(it.error)
                    is Resource.Success -> _uiState.value =
                        GamesState(games = it.data!!.games, next = it.data.next)
                }
            }.launchIn(viewModelScope)
    }

    fun getMoreGames() {
        getMoreGamesUseCase(_uiState.value.next).onEach {
            when (it) {
                is Resource.Error -> {
                    _uiState.value = GamesState(error = "Error")
                }
                is Resource.Loading -> {
                }
                is Resource.Success -> {
                    val list: MutableList<Games.Game> = _uiState.value.games.toMutableList()
                    val addedList: MutableList<Games.Game> =
                        it.data?.games?.toMutableList() ?: mutableListOf()
                    if (addedList.isNotEmpty()) list.addAll(addedList)
                    _uiState.value = GamesState(
                        games = list.toList(),
                        next = it.data?.next ?: ""
                    )
                }
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
        _uiState.value = GamesState(error = message)

    }

}