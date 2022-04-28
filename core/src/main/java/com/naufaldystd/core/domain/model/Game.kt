package com.naufaldystd.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Game(
	var gameId: Int,
	var name: String,
	var rating: Double,
	var metacritic: Int,
	var description: String,
	var playtime: Int,
	var released: String,
	var backgroundImage: String,
	var esrbRating: String,
	var tags: String,
	var platforms: String,
	var genres: String,
	var isFavorite: Boolean,
	) : Parcelable
