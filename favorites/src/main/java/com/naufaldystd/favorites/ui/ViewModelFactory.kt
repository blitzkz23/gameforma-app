package com.naufaldystd.favorites.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.naufaldystd.core.domain.usecase.RawgUseCase
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class ViewModelFactory @Inject constructor(private val rawgUseCase: RawgUseCase):
	ViewModelProvider.NewInstanceFactory() {
	override fun <T : ViewModel?> create(modelClass: Class<T>): T =
		when {
			modelClass.isAssignableFrom(FavoriteViewModel::class.java) -> {
				FavoriteViewModel(rawgUseCase) as T
			}
			else -> throw Throwable("Unkwnown Viewmodel class: " + modelClass.name)
		}
}