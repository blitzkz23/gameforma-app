package com.naufaldystd.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class GameResponse(
	@field:SerializedName("rating")
	var rating: Double,

	@field:SerializedName("playtime")
	var playtime: Int,

	@field:SerializedName("short_screenshots")
	var shortScreenshots: List<ShortScreenshotsItem>,

	@field:SerializedName("platforms")
	var platforms: List<PlatformsItem>,

	@field:SerializedName("rating_top")
	var ratingTop: Int,

	@field:SerializedName("genres")
	var genres: List<GenresItem>,

	@field:SerializedName("id")
	var id: Int,

	@field:SerializedName("ratings_count")
	var ratingsCount: Int,

	@field:SerializedName("released")
	var released: String,

	@field:SerializedName("tags")
	var tags: List<TagsItem>,

	@field:SerializedName("background_image")
	var backgroundImage: String,

	@field:SerializedName("tba")
	var tba: Boolean,

	@field:SerializedName("name")
	var name: String,

	@field:SerializedName("clip")
	var clip: Any,
)




