package com.naufaldystd.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "game")
data class GameEntity(
	@PrimaryKey
	@NonNull
	@ColumnInfo(name = "gameId")
	val gameId: Int,

	@ColumnInfo(name = "name")
	val name: String,

	@ColumnInfo(name = "rating")
	val rating: Double,

	@ColumnInfo(name = "metacritic")
	val metacritic: Int,

	@ColumnInfo(name = "description")
	val description : String,

	@ColumnInfo(name = "playtime")
	val playtime: Int,

	@ColumnInfo(name = "released")
	val released: String?,

	@ColumnInfo(name = "backgroundImage")
	val backgroundImage: String?,

	@ColumnInfo(name = "esrbRating")
	val esrbRating: String,

	@ColumnInfo(name = "tag")
	val tags : String,

	@ColumnInfo(name = "platforms")
	val platforms : String,

	@ColumnInfo(name = "genres")
	val genres : String,

	@ColumnInfo(name = "isFavorite")
	var isFavorite: Boolean = false,
)
