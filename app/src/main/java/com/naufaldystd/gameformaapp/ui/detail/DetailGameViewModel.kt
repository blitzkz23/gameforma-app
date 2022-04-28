package com.naufaldystd.gameformaapp.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.naufaldystd.core.domain.model.Game
import com.naufaldystd.core.domain.usecase.RawgUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailGameViewModel @Inject constructor(private val rawgUseCase: RawgUseCase): ViewModel() {
	fun getGameDetail(id: Int) = rawgUseCase.getGameDetail(id).asLiveData()
	fun setFavoriteGame(game: Game, newStatus: Boolean) =
		rawgUseCase.setFavoriteGame(game, newStatus)
}