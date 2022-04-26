package com.naufaldystd.core.domain.usecase

import com.naufaldystd.core.data.RawgRepository
import com.naufaldystd.core.data.Resource
import com.naufaldystd.core.domain.model.Game
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RawgInteractor @Inject constructor(private val rawgRepository: RawgRepository) : RawgUseCase {
	override fun getAllGame() = rawgRepository.getAllGame()

	override fun getFavoriteGame() = rawgRepository.getFavoriteGame()

	override fun setFavoriteGame(game: Game, state: Boolean) = rawgRepository.setFavoriteGame(game, state)
}