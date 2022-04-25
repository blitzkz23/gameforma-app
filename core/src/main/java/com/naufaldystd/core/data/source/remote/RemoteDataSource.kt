package com.naufaldystd.core.data.source.remote

import android.util.Log
import com.naufaldystd.core.data.source.remote.network.ApiService
import com.naufaldystd.core.data.source.remote.network.RawgApiResponse
import com.naufaldystd.core.data.source.remote.response.GameResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val apiService: ApiService) {

	private val apiKey = "a07a96102d4947b88583c551127a6247"

	suspend fun getAllGame(): Flow<RawgApiResponse<List<GameResponse>>> {
		// get data from remote api
		return flow {
			try {
				val response = apiService.getGamesList(apiKey)
				val dataArray = response.results
				if(dataArray.isNotEmpty()) {
					emit(RawgApiResponse.Success(response.results))
				} else {
					emit(RawgApiResponse.Empty)
				}
			} catch (e: Exception) {
				emit(RawgApiResponse.Error(e.toString()))
				Log.e("RemoteDataSource", e.toString())
			}
		}.flowOn(Dispatchers.IO)
	}
}