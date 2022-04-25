package com.naufaldystd.core.data.source.remote.network

sealed class RawgApiResponse<out T> {
	data class Success<T>(val data: T) : RawgApiResponse<T>()
	data class Error(val errorMessage: String) : RawgApiResponse<Nothing>()
	object Empty : RawgApiResponse<Nothing>()
}
