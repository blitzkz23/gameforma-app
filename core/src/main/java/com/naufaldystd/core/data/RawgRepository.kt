package com.naufaldystd.core.data

import com.naufaldystd.core.data.source.local.LocalDataSource
import com.naufaldystd.core.data.source.remote.RemoteDataSource
import com.naufaldystd.core.data.source.remote.network.RawgApiResponse
import com.naufaldystd.core.data.source.remote.response.GameResponse
import com.naufaldystd.core.domain.model.Game
import com.naufaldystd.core.domain.repository.IRawgRepository
import com.naufaldystd.core.utils.AppExecutors
import com.naufaldystd.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RawgRepository @Inject constructor(
	private val remoteDataSource: RemoteDataSource,
	private val localDataSource: LocalDataSource,
	private val appExecutors: AppExecutors
) : IRawgRepository {
	override fun getAllGame(): Flow<Resource<List<Game>>> =
		object : NetworkBoundResource<List<Game>, List<GameResponse>>() {
			override fun loadFromDB(): Flow<List<Game>> {
				return localDataSource.getAllGame().map {
					DataMapper.mapEntitiesToDomain(it)
				}
			}

			override fun shouldFetch(data: List<Game>?): Boolean =
				data == null || data.isEmpty()

			override suspend fun createCall(): Flow<RawgApiResponse<List<GameResponse>>> =
				remoteDataSource.getAllGames()

			override suspend fun saveCallResult(data: List<GameResponse>) {
				val gameList = DataMapper.mapResponsesToEntities(data)
				localDataSource.insertGame(gameList)
			}
		}.asFlow()

	override fun getFavoriteGame(): Flow<List<Game>> {
		return localDataSource.getFavoriteGame().map {
			DataMapper.mapEntitiesToDomain(it)
		}
	}

	override fun getGameDetail(id: Int): Flow<Resource<Game>> =
		object : NetworkBoundResource<Game, GameResponse>() {
			override fun loadFromDB(): Flow<Game>? {
				return localDataSource.getGameById(id)?.map {
					DataMapper.mapEntityToDomain(it)
				}
			}

			override fun shouldFetch(data: Game?): Boolean =
				data?.description == "" || data == null

			override suspend fun createCall(): Flow<RawgApiResponse<GameResponse>> =
				remoteDataSource.getGameDetail(id)

			override suspend fun saveCallResult(data: GameResponse) {
				val gameDetail = DataMapper.mapResponseToEntity(data)
				localDataSource.insertGame(gameDetail)
			}
		}.asFlow()

	override fun setFavoriteGame(game: Game, state: Boolean) {
		val gameEntity = DataMapper.mapDomainToEntity(game)
		appExecutors.diskIO().execute { localDataSource.setFavoriteGame(gameEntity, state) }
	}

	override suspend fun searchGame(query: String): Resource<List<Game>> {
		return when (val response = remoteDataSource.searchGame(query).first()) {
			is RawgApiResponse.Success -> {
				val gamesEntities = DataMapper.mapResponsesToEntities(response.data)
				val games = DataMapper.mapEntitiesToDomain(gamesEntities)
				localDataSource.insertGame(gamesEntities)
				Resource.Success(games)
			}
			is RawgApiResponse.Error -> {
				Resource.Error(response.errorMessage, null)
			}
			is RawgApiResponse.Empty -> {
				Resource.Error(response.toString(), null)
			}
		}
	}
}