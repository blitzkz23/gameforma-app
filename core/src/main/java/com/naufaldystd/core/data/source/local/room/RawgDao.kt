package com.naufaldystd.core.data.source.local.room

import androidx.room.*
import com.naufaldystd.core.data.source.local.entity.GameEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface RawgDao {

	@Query("SELECT * FROM game")
	fun getAllGame(): Flow<List<GameEntity>>

	@Query("SELECT * FROM game where isFavorite = 1")
	fun getFavoriteGame(): Flow<List<GameEntity>>

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insertGame(games: List<GameEntity>)

	@Update
	fun updateFavoriteGame(game: GameEntity)
}