package com.naufaldystd.core.data.source.remote.network

import com.naufaldystd.core.data.source.remote.response.ListGameResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
	@GET("games")
	suspend fun getGamesList(
		@Query("key") key: String
	): ListGameResponse
}