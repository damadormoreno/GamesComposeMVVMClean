package com.example.rawgcompose.features.games

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.rawgcompose.core.common.Resource
import com.example.rawgcompose.features.games.models.Games
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class GamesViewModel @Inject constructor(
    private val repository: GamesRepository,
    private val gamesUseCase: GetGamesUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(GamesState())
    val uiState: StateFlow<GamesState> = _uiState

     fun getGames(): Flow<PagingData<Games.Game>> {
        //return repository.getGames().cachedIn(viewModelScope)
         return gamesUseCase.invoke()
    }

}