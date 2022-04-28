package com.naufaldystd.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class GameResponse(
	@field:SerializedName("id")
	var id: Int,

	@field:SerializedName("name")
	var name: String,

	@field:SerializedName("rating")
	var rating: Double,

	@SerializedName("metacritic")
	val metacritic: Int?,

	@field:SerializedName("playtime")
	var playtime: Int,

	@field:SerializedName("released")
	var released: String?,

	@field:SerializedName("background_image")
	var backgroundImage: String?,

	@field:SerializedName("esrb_rating")
	val esrbRating: EsrbRatingItem?,

	@field:SerializedName("tags")
	val tags: List<TagsItem>?,

	@field:SerializedName("genres")
	val genres: List<GenresItem>?,

	@field:SerializedName("platforms")
	val platforms: List<PlatformsItem>?,

	@SerializedName("description_raw")
	val descriptionRaw: String
)




