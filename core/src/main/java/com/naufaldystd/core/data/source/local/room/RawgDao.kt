package com.naufaldystd.core.data.source.local.room

import androidx.room.*
import com.naufaldystd.core.data.source.local.entity.GameEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface RawgDao {

	@Query("SELECT * FROM game")
	fun getAllGame(): Flow<List<GameEntity>>

	@Query("SELECT * FROM game WHERE isFavorite = 1")
	fun getFavoriteGame(): Flow<List<GameEntity>>

	@Query("SELECT * FROM game WHERE gameId =:id")
	fun getGameById(id: Int): Flow<GameEntity>?

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insertGameList(games: List<GameEntity>)

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insertGame(game: GameEntity)

	@Update
	fun updateGameData(game: GameEntity)
}