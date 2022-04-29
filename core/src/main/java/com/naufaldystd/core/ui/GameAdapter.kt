package com.naufaldystd.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getColor
import androidx.core.content.ContextCompat.getDrawable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.naufaldystd.core.R
import com.naufaldystd.core.databinding.ItemListGameBinding
import com.naufaldystd.core.domain.model.Game

class GameAdapter : RecyclerView.Adapter<GameAdapter.ListViewHolder>() {

	private var listGame = ArrayList<Game>()
	var onItemClick: ((Game) -> Unit)? = null

	fun setData(newListData: List<Game>?) {
		if (newListData == null) return
		listGame.clear()
		listGame.addAll(newListData)
		notifyDataSetChanged()
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameAdapter.ListViewHolder =
		ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list_game, parent, false))

	override fun onBindViewHolder(holder: GameAdapter.ListViewHolder, position: Int) {
		val data = listGame[position]
		holder.bind(data)
	}

	override fun getItemCount(): Int = listGame.size

	inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
		private val binding = ItemListGameBinding.bind(itemView)
		fun bind(data: Game) {
			with(binding) {
				Glide.with(itemView.context)
					.load(data.backgroundImage)
					.apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
					.into(ivItemImage)
				tvItemTitle.text = data.name
				tvItemGenres.text = data.genres
				tvMetacriticScore.text = data.metacritic.toString()
				when (data.metacritic) {
					in 0..1 -> {
						hideMetascore(true, binding)
					}
					in 2..50 -> {
						linearLayout.background = getDrawable(itemView.context, R.drawable.bg_metascore_red)
						tvMetacriticScore.setTextColor(getColor(itemView.context, R.color.status_red))
					}
					in 51..74 -> {
						linearLayout.background = getDrawable(itemView.context, R.drawable.bg_metascore_yellow)
						tvMetacriticScore.setTextColor(getColor(itemView.context, R.color.status_yellow))
					}
					in 75..100 -> {
						hideMetascore(false, binding)
						linearLayout.background = getDrawable(itemView.context, R.drawable.bg_metascore_green)
						tvMetacriticScore.setTextColor(getColor(itemView.context, R.color.status_green))
					}
				}
			}
		}

		init {
			binding.root.setOnClickListener {
				onItemClick?.invoke(listGame[adapterPosition])
			}
		}
	}

	private fun hideMetascore(state: Boolean, binding: ItemListGameBinding) {
		if(state) {
			binding.linearLayout.visibility = View.GONE
			binding.tvMetacriticScore.visibility = View.GONE
		} else {
			binding.linearLayout.visibility = View.VISIBLE
			binding.tvMetacriticScore.visibility = View.VISIBLE
		}
	}
}