package com.naufaldystd.core.data.source.remote.response

import com.google.gson.annotations.SerializedName


data class TagsItem(

	@field:SerializedName("games_count")
	val gamesCount: Int,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("language")
	val language: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("image_background")
	val imageBackground: String,
)
