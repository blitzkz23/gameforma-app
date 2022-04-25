package com.naufaldystd.core.domain.repository

import com.naufaldystd.core.data.Resource
import com.naufaldystd.core.data.source.remote.response.GameResponse
import com.naufaldystd.core.domain.model.Game
import kotlinx.coroutines.flow.Flow

interface IRawgRepository {
	fun getAllGame(): Flow<Resource<List<Game>>>

	fun getFavoriteGame(): Flow<List<Game>>

	fun setFavoriteGame(game: Game, state: Boolean)
}