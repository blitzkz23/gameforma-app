package com.naufaldystd.gameformaapp.ui.search

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.naufaldystd.core.data.Resource
import com.naufaldystd.core.ui.GameAdapter
import com.naufaldystd.gameformaapp.R
import com.naufaldystd.gameformaapp.databinding.ActivitySearchBinding
import com.naufaldystd.gameformaapp.ui.detail.DetailGameActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchActivity : AppCompatActivity() {
	private val binding: ActivitySearchBinding by lazy {
		ActivitySearchBinding.inflate(layoutInflater)
	}
	private val searchViewModel: SearchViewModel by viewModels()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(binding.root)

		supportActionBar?.title = "Search"
		supportActionBar?.setDisplayHomeAsUpEnabled(true)
		supportActionBar?.setBackgroundDrawable(
			ColorDrawable(
				ContextCompat.getColor(
					this,
					R.color.secondary_black
				)
			)
		)

		val gameAdapter = GameAdapter()
		gameAdapter.onItemClick = { selectedGame ->
			val intent = Intent(this, DetailGameActivity::class.java)
			intent.putExtra(DetailGameActivity.DATA, selectedGame)
			startActivity(intent)
		}
		searchViewModel.gamesResult.observe(this) { results ->
			if (results != null) {
				when (results) {
					is Resource.Loading -> showLoading(true)
					is Resource.Success -> {
						showLoading(false)
						gameAdapter.setData(results.data)
					}
					is Resource.Error -> {
						Toast.makeText(this, "error", Toast.LENGTH_SHORT).show()
					}
				}
			}
		}

		with(binding.rvSearch) {
			layoutManager = LinearLayoutManager(this@SearchActivity)
			setHasFixedSize(true)
			adapter = gameAdapter
		}

		binding.searchInput.addTextChangedListener(object : TextWatcher {
			override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
			}

			override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
				showSearchHint(false)
				showLoading(true)
			}

			override fun afterTextChanged(p0: Editable?) {
				showLoading(false)
				val query = p0.toString().trim()
				lifecycleScope.launch {
					searchViewModel.searchGame(query)
				}
			}

		})

	}

	private fun showSearchHint(state: Boolean) {
		if (state) {
			binding.beginSearch.visibility = View.VISIBLE
			binding.searchHintAnim.visibility = View.VISIBLE
		} else {
			binding.beginSearch.visibility = View.GONE
			binding.searchHintAnim.visibility = View.GONE
		}
	}

	private fun showLoading(state: Boolean) {
		if (state) {
			binding.searchLoading.visibility = View.VISIBLE
		} else {
			binding.searchLoading.visibility = View.GONE
		}
	}
}