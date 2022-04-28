package com.naufaldystd.favorites.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.naufaldystd.core.ui.GameAdapter
import com.naufaldystd.favorites.databinding.FragmentFavoriteBinding
import com.naufaldystd.favorites.di.DaggerFavoriteComponent
import com.naufaldystd.gameformaapp.di.FavoriteModuleDependencies
import com.naufaldystd.gameformaapp.ui.detail.DetailGameActivity
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class FavoriteFragment : Fragment() {

	private var _binding: FragmentFavoriteBinding? = null

	// This property is only valid between onCreateView and
	// onDestroyView.
	private val binding get() = _binding!!

	@Inject
	lateinit var factory: ViewModelFactory

	private val favoriteViewModel: FavoriteViewModel by viewModels {
		factory
	}

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {

		_binding = FragmentFavoriteBinding.inflate(inflater, container, false)
		return binding.root
	}

	override fun onAttach(context: Context) {
		super.onAttach(context)
		DaggerFavoriteComponent.builder()
			.context(context)
			.appDependencies(
				EntryPointAccessors.fromApplication(
					context.applicationContext,
					FavoriteModuleDependencies::class.java
				)
			)
			.build()
			.inject(this)
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		val gameAdapter = GameAdapter()
		favoriteViewModel.getFavoriteGame().observe(viewLifecycleOwner) { favoriteGames ->
			gameAdapter.setData(favoriteGames)
		}

		gameAdapter.onItemClick = { intentData ->
			val intent = Intent(activity, DetailGameActivity::class.java)
			intent.putExtra(DetailGameActivity.DATA, intentData)
			startActivity(intent)
		}

		with (binding.rvFavorite) {
			layoutManager = LinearLayoutManager(context)
			setHasFixedSize(true)
			adapter = gameAdapter
		}
	}

	override fun onDestroyView() {
		super.onDestroyView()
		_binding = null
	}
}