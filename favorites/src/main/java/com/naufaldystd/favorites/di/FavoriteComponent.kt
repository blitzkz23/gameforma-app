package com.naufaldystd.favorites.di

import android.content.Context
import com.naufaldystd.favorites.ui.FavoriteFragment
import com.naufaldystd.gameformaapp.di.FavoriteModuleDependencies
import dagger.BindsInstance
import dagger.Component

@Component(dependencies = [FavoriteModuleDependencies::class])
interface FavoriteComponent {

	fun inject(fragment: FavoriteFragment)

	@Component.Builder
	interface Builder {
		fun context(@BindsInstance context: Context): Builder
		fun appDependencies(favoriteModuleDependencies: FavoriteModuleDependencies): Builder
		fun build(): FavoriteComponent
	}
}