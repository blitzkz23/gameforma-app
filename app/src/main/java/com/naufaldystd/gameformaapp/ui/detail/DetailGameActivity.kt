package com.naufaldystd.gameformaapp.ui.detail

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.naufaldystd.core.data.Resource
import com.naufaldystd.core.domain.model.Game
import com.naufaldystd.core.utils.Constants.EXTRA_DATA
import com.naufaldystd.gameformaapp.R
import com.naufaldystd.gameformaapp.databinding.ActivityDetailBinding
import com.naufaldystd.gameformaapp.databinding.ContentDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailGameActivity : AppCompatActivity() {
	private val binding: ActivityDetailBinding by lazy {
		ActivityDetailBinding.inflate(layoutInflater)
	}
	private var contentBinding: ContentDetailBinding? = null
	private val detailGameViewModel: DetailGameViewModel by viewModels()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		contentBinding = binding.detailContent
		setContentView(binding.root)

		setSupportActionBar(binding.toolbar)
		supportActionBar?.setDisplayHomeAsUpEnabled(true)

		val data = intent.getParcelableExtra<Game>(DATA)
		if (data != null) {
			detailGameViewModel.getGameDetail(data.gameId).observe(this) { detailGame ->
				when (detailGame) {
					is Resource.Loading -> {
						showContent(false)
					}
					is Resource.Success -> {
						showContent(true)
						val updatedData = detailGame.data as Game
						showGameDetail(updatedData)
					}
					is Resource.Error -> {
						Toast.makeText(this, "error : ${detailGame.message}", Toast.LENGTH_SHORT)
							.show()
					}
				}
			}
		}
	}

	private fun showGameDetail(detailGame: Game?) {
		detailGame?.let {
			Glide.with(this@DetailGameActivity)
				.load(detailGame.backgroundImage)
				.apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_loading))
				.into(binding.ivDetailImage)
			supportActionBar?.title = detailGame.name
			contentBinding?.apply {
				tvGameReleaseDate.text = detailGame.released
				tvGamePlaytime.text = getString(R.string.playtime_placeholder, detailGame.playtime)
				tvGameTitle.text = detailGame.name
				tvGameDescription.text = detailGame.description
				tvPlatforms.text = detailGame.platforms
				tvAge.text = detailGame.esrbRating
				tvMetacriticScore.text = detailGame.metacritic.toString()
				tvTags.text = detailGame.tags

				when (detailGame.metacritic) {
					in 1..50 -> {
						linearLayout.background = ContextCompat.getDrawable(
							this@DetailGameActivity,
							com.naufaldystd.core.R.drawable.bg_metascore_red
						)
						tvMetacriticScore.setTextColor(
							ContextCompat.getColor(
								this@DetailGameActivity,
								com.naufaldystd.core.R.color.status_red
							)
						)
					}
					in 51..69 -> {
						linearLayout.background = ContextCompat.getDrawable(
							this@DetailGameActivity,
							com.naufaldystd.core.R.drawable.bg_metascore_yellow
						)
						tvMetacriticScore.setTextColor(
							ContextCompat.getColor(
								this@DetailGameActivity,
								com.naufaldystd.core.R.color.status_yellow
							)
						)
					}
				}
			}

			var statusFavorite = detailGame.isFavorite
			setStatusFavorite(statusFavorite)
			binding.fabFavorite.setOnClickListener {
				statusFavorite = !statusFavorite
				detailGameViewModel.setFavoriteGame(detailGame, statusFavorite)
				setStatusFavorite(statusFavorite)
			}
		}
	}

	private fun setStatusFavorite(statusFavorite: Boolean) {
		if (statusFavorite) {
			binding.fabFavorite.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_baseline_favorite))
		} else {
			binding.fabFavorite.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_unfavorite))
		}
	}

	private fun showContent(state: Boolean) {
		if (!state) {
			binding.loadingAnimation.visibility = View.VISIBLE
			contentBinding?.apply {
				tvGameReleaseDate.visibility = View.GONE
				linearLayout.visibility = View.GONE
				about.visibility = View.GONE
				platforms.visibility = View.GONE
				metascore.visibility = View.GONE
				age.visibility = View.GONE
				tags.visibility = View.GONE
			}
		} else {
			binding.loadingAnimation.visibility = View.GONE
			contentBinding?.apply {
				tvGameReleaseDate.visibility = View.VISIBLE
				linearLayout.visibility = View.VISIBLE
				about.visibility = View.VISIBLE
				platforms.visibility = View.VISIBLE
				metascore.visibility = View.VISIBLE
				age.visibility = View.VISIBLE
				tags.visibility = View.VISIBLE
			}
		}
	}

	companion object {
		const val DATA = EXTRA_DATA
	}
}