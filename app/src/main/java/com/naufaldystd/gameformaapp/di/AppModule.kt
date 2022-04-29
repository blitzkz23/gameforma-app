package com.naufaldystd.gameformaapp.di

import android.app.Application
import android.content.Context
import com.naufaldystd.core.domain.usecase.RawgInteractor
import com.naufaldystd.core.domain.usecase.RawgUseCase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

	@Binds
	@Singleton
	abstract fun provideRawgUseCase(rawgInteractor: RawgInteractor): RawgUseCase
}