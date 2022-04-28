package com.naufaldystd.gameformaapp.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.naufaldystd.core.data.Resource
import com.naufaldystd.core.domain.model.Game
import com.naufaldystd.core.domain.usecase.RawgUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val rawgUseCase: RawgUseCase): ViewModel() {
	private var _gamesResult = MutableLiveData<Resource<List<Game>>>()
	val gamesResult: LiveData<Resource<List<Game>>> get() = _gamesResult

	suspend fun searchGame(query: String) {
		_gamesResult.value = rawgUseCase.searchGame(query)
	}
}