package com.naufaldystd.core.data.source.remote

import android.util.Log
import com.naufaldystd.core.data.source.remote.network.ApiService
import com.naufaldystd.core.data.source.remote.network.RawgApiResponse
import com.naufaldystd.core.data.source.remote.response.GameResponse
import com.naufaldystd.core.utils.Constants.API_KEY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val apiService: ApiService) {

	suspend fun getAllGames(): Flow<RawgApiResponse<List<GameResponse>>> {
		// get data from remote api
		return flow {
			try {
				val response = apiService.getGamesList(API_KEY)
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

	suspend fun getGameDetail(id: Int): Flow<RawgApiResponse<GameResponse>> {
		return flow<RawgApiResponse<GameResponse>> {
			try {
				val response = apiService.getGameDetail(id.toString(), API_KEY)
				if (response.descriptionRaw.isNotEmpty()) {
					emit(RawgApiResponse.Success(response))
				} else {
					emit(RawgApiResponse.Empty)
				}
			} catch (e: Exception) {
				emit(RawgApiResponse.Error(e.toString()))
				Log.e("RemoteDataSource", e.toString())
			}
		}.flowOn(Dispatchers.IO)
	}

	fun searchGame(query: String): Flow<RawgApiResponse<List<GameResponse>>> {
		return flow {
			try {
				val response = apiService.searchGames(query, API_KEY)
				val dataArray = response.results
				if (dataArray.isNotEmpty()) {
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