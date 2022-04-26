package com.naufaldystd.core.domain.repository

import com.naufaldystd.core.data.Resource
import com.naufaldystd.core.domain.model.Game
import kotlinx.coroutines.flow.Flow

interface IRawgRepository {
	fun getAllGame(): Flow<Resource<List<Game>>>
	fun getFavoriteGame(): Flow<List<Game>>
	fun getGameDetail(id: Int): Flow<Resource<Game>>
	fun setFavoriteGame(game: Game, state: Boolean)
	suspend fun searchGame(query: String): Resource<List<Game>>
}