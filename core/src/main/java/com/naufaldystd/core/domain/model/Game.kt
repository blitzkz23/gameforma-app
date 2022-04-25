package com.naufaldystd.core.domain.model

import com.naufaldystd.core.data.source.remote.response.GenresItem
import com.naufaldystd.core.data.source.remote.response.PlatformsItem
import com.naufaldystd.core.data.source.remote.response.ShortScreenshotsItem
import com.naufaldystd.core.data.source.remote.response.TagsItem

data class Game(
	var gameId: Int,
	var rating: Double,
	var playtime: Int,
	var released: String,
	var backgroundImage: String,
	var tba: Boolean,
	var name: String,
)
