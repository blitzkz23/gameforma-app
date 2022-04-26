package com.naufaldystd.core.utils

import com.naufaldystd.core.data.source.local.entity.GameEntity
import com.naufaldystd.core.data.source.remote.response.GameResponse
import com.naufaldystd.core.domain.model.Game

object DataMapper {
	fun mapResponseToEntities(input: List<GameResponse>) : List<GameEntity> {
		val gameList = ArrayList<GameEntity>()
		input.map {
			val platforms = getPlatformName(it)
			val genres = getGenreName(it)
			val game = GameEntity(
				gameId = it.id,
				name = it.name,
				rating = it.rating,
				metacritic = it.metacritic ?: 0,
				description = "",
				playtime = it.playtime,
				released = it.released,
				backgroundImage = it.backgroundImage,
				esrbRating = it.esrbRating?.name ?: "",
				tags = "",
				platforms = platforms,
				genres = genres
			)
			gameList.add(game)
		}
		return gameList
	}

	fun mapEntitiesToDomain(input: List<GameEntity>) : List<Game> =
		input.map {
			Game(
				gameId = it.gameId,
				name = it.name,
				rating = it.rating,
				metacritic = it.metacritic,
				playtime = it.playtime,
				released = it.released,
				backgroundImage = it.backgroundImage,
				description = it.description,
				esrbRating = it.esrbRating,
				tags = it.tags,
				platforms = it.platforms,
				genres = it.genres,
			)
		}

	fun mapDomainToEntity(input: Game) = GameEntity(
		gameId = input.gameId,
		name = input.name,
		rating = input.rating,
		metacritic = input.metacritic,
		playtime = input.playtime,
		released = input.released,
		backgroundImage = input.backgroundImage,
		description = input.description,
		esrbRating = input.esrbRating,
		tags = input.tags,
		platforms = input.platforms,
		genres = input.genres,
	)

	private fun getPlatformName(data: GameResponse): String {
		val result = StringBuilder().append("")

		for (i in data.platforms.indices) {
			if (i < data.platforms.size - 1) {
				result.append("${data.platforms[i].platform.name}, ")
			} else {
				result.append(data.platforms[i].platform.name)
			}
		}

		return result.toString()
	}

	private fun getGenreName(data: GameResponse): String {
		val result = StringBuilder().append("")

		for (i in data.genres.indices) {
			if (i < data.genres.size - 1) {
				result.append("${data.genres[i].name}, ")
			} else {
				result.append(data.genres[i].name)
			}
		}

		return result.toString()
	}
}