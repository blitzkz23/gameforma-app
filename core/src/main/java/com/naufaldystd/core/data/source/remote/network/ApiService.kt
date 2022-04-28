package com.naufaldystd.core.data.source.remote.network

import com.naufaldystd.core.data.source.remote.response.GameResponse
import com.naufaldystd.core.data.source.remote.response.ListGameResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

	@GET("games")
	suspend fun getGamesList(
		@Query("key") key: String
	): ListGameResponse

	@GET("games/{gameId}")
	suspend fun getGameDetail(
		@Path("gameId") gameId: Int,
		@Query("key") key: String
	): GameResponse

	@GET("games")
	suspend fun searchGames(
		@Query("search") search: String,
		@Query("key") key: String
	): ListGameResponse
}