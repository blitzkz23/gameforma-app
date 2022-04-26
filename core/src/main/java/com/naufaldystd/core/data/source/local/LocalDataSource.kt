package com.naufaldystd.core.data.source.local

import com.naufaldystd.core.data.source.local.entity.GameEntity
import com.naufaldystd.core.data.source.local.room.RawgDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val rawgDao: RawgDao) {

	fun getAllGame(): Flow<List<GameEntity>> = rawgDao.getAllGame()

	fun getFavoriteGame(): Flow<List<GameEntity>> = rawgDao.getFavoriteGame()

	fun getGameById(id: Int): Flow<GameEntity>? = rawgDao.getGameById(id)

	suspend fun insertGame(gameList: List<GameEntity>) = rawgDao.insertGames(gameList)

	suspend fun insertGame(game: GameEntity) = rawgDao.insertGame(game)

	fun setFavoriteGame(game: GameEntity, newState: Boolean) {
		game.isFavorite = newState
		rawgDao.updateFavoriteGame(game)
	}
}