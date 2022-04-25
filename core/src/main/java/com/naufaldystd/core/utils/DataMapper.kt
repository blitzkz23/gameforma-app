package com.naufaldystd.core.utils

import com.naufaldystd.core.data.source.local.entity.GameEntity
import com.naufaldystd.core.data.source.remote.response.GameResponse
import com.naufaldystd.core.domain.model.Game

object DataMapper {
	fun mapResponseToEntities(input: List<GameResponse>) : List<GameEntity> {
		val gameList = ArrayList<GameEntity>()
		input.map {
			val game = GameEntity(
				gameId = it.id,
				rating = it.rating,
				playtime = it.playtime,
				released = it.released,
				backgroundImage = it.backgroundImage,
				tba = it.tba,
				name = it.name,
			)
			gameList.add(game)
		}
		return gameList
	}

	fun mapEntitiesToDomain(input: List<GameEntity>) : List<Game> =
		input.map {
			Game(
				gameId = it.gameId,
				rating = it.rating,
				playtime = it.playtime,
				released = it.released,
				backgroundImage = it.backgroundImage,
				tba = it.tba,
				name = it.name
			)
		}

	fun mapDomainToEntity(input: Game) = GameEntity(
		gameId = input.gameId,
		rating = input.rating,
		playtime = input.playtime,
		released = input.released,
		backgroundImage = input.backgroundImage,
		tba = input.tba,
		name = input.name
	)
}