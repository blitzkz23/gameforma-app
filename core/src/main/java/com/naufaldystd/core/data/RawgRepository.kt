package com.naufaldystd.core.data

import com.naufaldystd.core.data.source.local.LocalDataSource
import com.naufaldystd.core.data.source.remote.RemoteDataSource
import com.naufaldystd.core.data.source.remote.response.GameResponse
import com.naufaldystd.core.domain.model.Game
import com.naufaldystd.core.domain.repository.IRawgRepository
import com.naufaldystd.core.utils.AppExecutors
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RawgRepository @Inject constructor(
	private val remoteDataSource: RemoteDataSource,
	private val localDataSource: LocalDataSource,
	private val appExecutors: AppExecutors
): IRawgRepository {
	override fun getAllGame(): Flow<Resource<List<Game>>> {
		TODO("Not yet implemented")
	}

	override fun getFavoriteGame(): Flow<List<Game>> {
		TODO("Not yet implemented")
	}

	override fun setFavoriteGame(game: Game, state: Boolean) {
		TODO("Not yet implemented")
	}
}