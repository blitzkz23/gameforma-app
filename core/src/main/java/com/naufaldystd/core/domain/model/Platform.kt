package com.naufaldystd.core.domain.model

import com.google.gson.annotations.SerializedName

data class Platform(

	val image: Any,
	val gamesCount: Int,
	val yearEnd: Any,
	val yearStart: Any,
	val name: String,
	val id: Int,
	val imageBackground: String,
)
