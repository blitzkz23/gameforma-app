package com.naufaldystd.gameformaapp.di

import com.naufaldystd.core.domain.usecase.RawgUseCase
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface FavoriteModuleDependencies {
	fun rawgUseCase(): RawgUseCase
}