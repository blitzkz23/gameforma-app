package com.naufaldystd.core.domain.usecase

import com.naufaldystd.core.data.RawgRepository
import com.naufaldystd.core.domain.model.Game
import javax.inject.Inject

class RawgInteractor @Inject constructor(private val rawgRepository: RawgRepository) : RawgUseCase {
	override fun getAllGame() = rawgRepository.getAllGame()

	override fun getFavoriteGame() = rawgRepository.getFavoriteGame()

	override fun getGameDetail(id: Int) = rawgRepository.getGameDetail(id)

	override fun setFavoriteGame(game: Game, state: Boolean) = rawgRepository.setFavoriteGame(game, state)

	override suspend fun searchGame(query: String) = rawgRepository.searchGame(query)
}