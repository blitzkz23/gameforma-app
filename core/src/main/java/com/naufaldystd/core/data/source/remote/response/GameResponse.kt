package com.naufaldystd.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class GameResponse(
	@field:SerializedName("rating")
	val rating: Double,

	@field:SerializedName("playtime")
	val playtime: Int,

	@field:SerializedName("short_screenshots")
	val shortScreenshots: List<ShortScreenshotsItem>,

	@field:SerializedName("platforms")
	val platforms: List<PlatformsItem>,

	@field:SerializedName("rating_top")
	val ratingTop: Int,

	@field:SerializedName("reviews_text_count")
	val reviewsTextCount: Int,

	@field:SerializedName("genres")
	val genres: List<GenresItem>,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("ratings_count")
	val ratingsCount: Int,

	@field:SerializedName("released")
	val released: String,

	@field:SerializedName("tags")
	val tags: List<TagsItem>,

	@field:SerializedName("background_image")
	val backgroundImage: String,

	@field:SerializedName("tba")
	val tba: Boolean,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("clip")
	val clip: Any,

	@field:SerializedName("reviews_count")
	val reviewsCount: Int
)




