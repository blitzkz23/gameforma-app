package com.naufaldystd.gameformaapp.ui.home

import androidx.lifecycle.*
import com.naufaldystd.core.domain.usecase.RawgUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(rawgUseCase: RawgUseCase) : ViewModel() {
	val games = rawgUseCase.getAllGame().asLiveData()
}