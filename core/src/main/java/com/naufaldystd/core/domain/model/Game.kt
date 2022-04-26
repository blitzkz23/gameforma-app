package com.naufaldystd.core.domain.model

data class Game(
	var gameId: Int,
	var rating: Double,
	var playtime: Int,
	var released: String,
	var backgroundImage: String,
	var tba: Boolean,
	var name: String,
)
