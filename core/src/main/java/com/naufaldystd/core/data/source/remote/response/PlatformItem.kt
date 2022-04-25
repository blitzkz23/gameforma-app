package com.naufaldystd.core.data.source.remote.response

import com.google.gson.annotations.SerializedName


data class PlatformItem(

	@field:SerializedName("image")
	val image: Any,

	@field:SerializedName("games_count")
	val gamesCount: Int,

	@field:SerializedName("year_end")
	val yearEnd: Any,

	@field:SerializedName("year_start")
	val yearStart: Any,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("image_background")
	val imageBackground: String,
)