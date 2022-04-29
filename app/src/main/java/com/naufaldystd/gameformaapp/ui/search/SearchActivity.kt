package com.naufaldystd.gameformaapp.ui.search

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.naufaldystd.core.data.Resource
import com.naufaldystd.core.ui.GameAdapter
import com.naufaldystd.gameformaapp.R
import com.naufaldystd.gameformaapp.databinding.ActivitySearchBinding
import com.naufaldystd.gameformaapp.ui.detail.DetailGameActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.launch

@AndroidEntryPoint
@ExperimentalCoroutinesApi
@FlowPreview
class SearchActivity : AppCompatActivity() {
	private val binding: ActivitySearchBinding by lazy {
		ActivitySearchBinding.inflate(layoutInflater)
	}
	private val searchViewModel: SearchViewModel by viewModels()

	@OptIn(ObsoleteCoroutinesApi::class)
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
			lifecycleScope.launch {
				searchViewModel.insertGame(selectedGame)
			}
			startActivity(intent)
		}

		searchViewModel.searchResult.observe(this) { results ->
			if (results != null) {
				when (results) {
					is Resource.Loading -> showLoading(true)
					is Resource.Success -> {
						showLoading(false)
						gameAdapter.setData(results.data)
					}
					is Resource.Error -> {
						Toast.makeText(this, "error", Toast.LENGTH_SHORT).show()
						Log.e("SearchActivity", "${results.message}")
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
				lifecycleScope.launch {
					searchViewModel.queryChannel.send(p0.toString())
				}
			}

		})

	}

	private fun showSearchHint(state: Boolean) {
		binding.beginSearch.isVisible = state
		binding.searchHintAnim.isVisible = state
	}


	private fun showLoading(state: Boolean) {
		binding.searchLoading.isVisible = state
		binding.searchLoading.isVisible = state
	}
}