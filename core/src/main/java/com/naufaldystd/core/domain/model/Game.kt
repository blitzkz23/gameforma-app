package com.naufaldystd.core.domain.model

import com.naufaldystd.core.data.source.remote.response.GenresItem
import com.naufaldystd.core.data.source.remote.response.PlatformsItem
import com.naufaldystd.core.data.source.remote.response.ShortScreenshotsItem
import com.naufaldystd.core.data.source.remote.response.TagsItem

data class Game(
	var rating: Double,
	var playtime: Int,
	var shortScreenshots: List<ShortScreenshotsItem>,
	var platforms: List<PlatformsItem>,
	var ratingTop: Int,
	var genres: List<GenresItem>,
	var id: Int,
	var ratingsCount: Int,
	var released: String,
	var tags: List<TagsItem>,
	var backgroundImage: String,
	var tba: Boolean,
	var name: String,
	var clip: Any,
)
