package com.naufaldystd.core.domain.repository

import com.naufaldystd.core.data.Resource
import com.naufaldystd.core.data.source.remote.response.GameResponse
import kotlinx.coroutines.flow.Flow

interface IRawgRepository {
	fun getAllGame(): Flow<Resource<List<GameResponse>>>

	fun getFavoriteGame(): Flow<List<GameResponse>>

	fun setFavoriteGame(game: GameResponse, state: Boolean)
}