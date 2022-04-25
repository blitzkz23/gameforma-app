package com.naufaldystd.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class GameResponse(
	@field:SerializedName("rating")
	var rating: Double,

	@field:SerializedName("playtime")
	var playtime: Int,

	@field:SerializedName("id")
	var id: Int,

	@field:SerializedName("released")
	var released: String,

	@field:SerializedName("background_image")
	var backgroundImage: String,

	@field:SerializedName("tba")
	var tba: Boolean,

	@field:SerializedName("name")
	var name: String,
)




