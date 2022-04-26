package com.naufaldystd.core.domain.usecase

import com.naufaldystd.core.data.Resource
import com.naufaldystd.core.domain.model.Game
import kotlinx.coroutines.flow.Flow

interface RawgUseCase {
	fun getAllGame(): Flow<Resource<List<Game>>>
	fun getFavoriteGame(): Flow<List<Game>>
	fun setFavoriteGame(game: Game, state: Boolean)
}