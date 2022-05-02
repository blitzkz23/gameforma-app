package com.naufaldystd.core.di

import android.content.Context
import androidx.room.Room
import com.naufaldystd.core.data.source.local.room.RawgDao
import com.naufaldystd.core.data.source.local.room.RawgDatabase
import com.naufaldystd.core.utils.Constants.DB_NAME
import com.naufaldystd.core.utils.Constants.PASSPHRASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

	@Singleton
	@Provides
	fun provideDatabase(@ApplicationContext context: Context): RawgDatabase {
		val passphrase: ByteArray = SQLiteDatabase.getBytes(PASSPHRASE.toCharArray())
		val factory = SupportFactory(passphrase)

		return Room.databaseBuilder(
			context,
			RawgDatabase::class.java, DB_NAME
		)
			.fallbackToDestructiveMigration()
			.openHelperFactory(factory)
			.build()
	}

	@Provides
	fun provideRawgDao(database: RawgDatabase): RawgDao = database.rawgDao()
}