package com.naufaldystd.core.di

import android.content.Context
import androidx.room.Room
import com.naufaldystd.core.data.source.local.room.RawgDao
import com.naufaldystd.core.data.source.local.room.RawgDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

	@Singleton
	@Provides
	fun provideDatabase(@ApplicationContext context: Context): RawgDatabase = Room.databaseBuilder(
		context,
		RawgDatabase::class.java, "Rawg.db"
	).fallbackToDestructiveMigration().build()

	@Provides
	fun provideRawgDao(database: RawgDatabase): RawgDao = database.rawgDao()
}