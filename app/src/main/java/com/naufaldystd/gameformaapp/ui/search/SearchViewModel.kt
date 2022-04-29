package com.naufaldystd.gameformaapp.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.naufaldystd.core.domain.model.Game
import com.naufaldystd.core.domain.usecase.RawgUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
@FlowPreview
@ExperimentalCoroutinesApi
class SearchViewModel @Inject constructor(private val rawgUseCase: RawgUseCase): ViewModel() {
	suspend fun insertGame(game: Game) = rawgUseCase.insertGameDetail(game)

	@OptIn(ObsoleteCoroutinesApi::class)
	val queryChannel = BroadcastChannel<String>(Channel.CONFLATED)
	val searchResult = queryChannel.asFlow()
		.debounce(300)
		.distinctUntilChanged()
		.filter { it.trim().isNotEmpty() }
		.mapLatest {
			rawgUseCase.searchGame(it)
		}
		.asLiveData()

}