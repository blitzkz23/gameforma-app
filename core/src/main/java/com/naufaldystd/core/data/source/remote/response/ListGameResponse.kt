package com.naufaldystd.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListGameResponse(

	@field:SerializedName("next")
	val next: String,

	@field:SerializedName("previous")
	val previous: Any,

	@field:SerializedName("count")
	val count: Int,

	@field:SerializedName("results")
	val results: List<GameResponse>
)

