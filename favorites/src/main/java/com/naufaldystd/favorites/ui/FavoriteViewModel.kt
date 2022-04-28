package com.naufaldystd.favorites.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.naufaldystd.core.domain.usecase.RawgUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(private val rawgUseCase: RawgUseCase): ViewModel() {
	fun getFavoriteGame() = rawgUseCase.getFavoriteGame().asLiveData()
}