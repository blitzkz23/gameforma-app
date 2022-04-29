package com.naufaldystd.gameformaapp.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.naufaldystd.core.domain.usecase.RawgUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(rawgUseCase: RawgUseCase) : ViewModel() {
	val games = rawgUseCase.getAllGame().asLiveData()
}