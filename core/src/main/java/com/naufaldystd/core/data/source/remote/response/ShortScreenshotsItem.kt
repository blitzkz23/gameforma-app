package com.naufaldystd.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ShortScreenshotsItem(

	@field:SerializedName("image")
	val image: String,

	@field:SerializedName("id")
	val id: Int
)
