package com.naufaldystd.core.data.source.remote.response

import com.google.gson.annotations.SerializedName


data class EsrbRatingItem(

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("slug")
	val slug: String
)
