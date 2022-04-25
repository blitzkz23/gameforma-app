package com.naufaldystd.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class GameDetailResponse(

	@field:SerializedName("added")
	val added: Int,

	@field:SerializedName("developers")
	val developers: List<DevelopersItem>,

	@field:SerializedName("name_original")
	val nameOriginal: String,

	@field:SerializedName("rating")
	val rating: Double,

	@field:SerializedName("game_series_count")
	val gameSeriesCount: Int,

	@field:SerializedName("playtime")
	val playtime: Int,

	@field:SerializedName("platforms")
	val platforms: List<PlatformsItem>,

	@field:SerializedName("rating_top")
	val ratingTop: Int,

	@field:SerializedName("reviews_text_count")
	val reviewsTextCount: Int,

	@field:SerializedName("publishers")
	val publishers: List<PublishersResponse>,

	@field:SerializedName("achievements_count")
	val achievementsCount: Int,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("ratings_count")
	val ratingsCount: Int,

	@field:SerializedName("slug")
	val slug: String,

	@field:SerializedName("released")
	val released: String,

	@field:SerializedName("description_raw")
	val descriptionRaw: String,

	@field:SerializedName("tags")
	val tags: List<TagsItem>,

	@field:SerializedName("background_image")
	val backgroundImage: String,

	@field:SerializedName("tba")
	val tba: Boolean,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("description")
	val description: String,

	@field:SerializedName("genres")
	val genres: List<GenresItem>,

	@field:SerializedName("parent_achievements_count")
	val parentAchievementsCount: Int,

	@field:SerializedName("website")
	val website: String,

	@field:SerializedName("background_image_additional")
	val backgroundImageAdditional: String,

	@field:SerializedName("esrb_rating")
	val esrbRating: EsrbRatingItem,

	@field:SerializedName("screenshots_count")
	val screenshotsCount: Int,

	@field:SerializedName("clip")
	val clip: Any
)





