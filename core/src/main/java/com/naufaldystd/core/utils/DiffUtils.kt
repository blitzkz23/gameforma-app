package com.naufaldystd.core.utils

import androidx.recyclerview.widget.DiffUtil
import com.naufaldystd.core.domain.model.Game

class DiffUtils(private val oldList: List<Game>, private val newList: List<Game>) :
	DiffUtil.Callback() {
	override fun getOldListSize(): Int = oldList.size

	override fun getNewListSize(): Int = newList.size

	override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
		return oldList[oldItemPosition].gameId == newList[newItemPosition].gameId
	}

	override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
		val (gameId, name, rating, metacritic, description, playtime, released, backgroundImage, esrbRating,
			tags, platforms, genres, isFavorite) = oldList[oldItemPosition]
		val (gameId2, name2, rating2, metacritic2, description2, playtime2, released2, backgroundImage2, esrbRating2,
			tags2, platforms2, genres2, isFavorite2) = newList[newItemPosition]

		return gameId == gameId2
				&& name == name2
				&& rating == rating2
				&& metacritic == metacritic2
				&& description == description2
				&& playtime == playtime2
				&& released == released2
				&& backgroundImage == backgroundImage2
				&& esrbRating == esrbRating2
				&& tags == tags2
				&& platforms == platforms2
				&& genres == genres2
				&& isFavorite == isFavorite2
	}

}