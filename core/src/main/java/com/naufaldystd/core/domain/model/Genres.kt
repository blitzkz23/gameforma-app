package com.naufaldystd.core.domain.model

import com.google.gson.annotations.SerializedName

data class Genres(

	val gamesCount: Int,
	val name: String,
	val id: Int,
	val imageBackground: String,
	val slug: String
)
