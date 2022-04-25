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

	@ColumnInfo(name = "rating")
	val rating: Double,

	@ColumnInfo(name = "playtime")
	val playtime: Int,

	@ColumnInfo(name = "released")
	val released: String,

	@ColumnInfo(name = "backgroundImage")
	val backgroundImage: String,

	@ColumnInfo(name = "tba")
	val tba: Boolean,

	@ColumnInfo(name = "name")
	val name: String,

	@ColumnInfo(name = "isFavorite")
	var isFavorite: Boolean = false,
)
