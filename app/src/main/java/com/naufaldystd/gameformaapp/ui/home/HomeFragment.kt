package com.naufaldystd.gameformaapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.naufaldystd.core.data.Resource
import com.naufaldystd.core.ui.GameAdapter
import com.naufaldystd.gameformaapp.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

	private var _binding: FragmentHomeBinding? = null

	// This property is only valid between onCreateView and
	// onDestroyView.
	private val binding get() = _binding!!
	private val homeViewModel : HomeViewModel by viewModels()

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		_binding = FragmentHomeBinding.inflate(inflater, container, false)
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		if (activity != null) {
			val gameAdapter = GameAdapter()

			homeViewModel.games.observe(viewLifecycleOwner) { games ->
				if (games != null) {
					when (games) {
						is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
						is Resource.Success -> {
							binding.progressBar.visibility = View.GONE
							gameAdapter.setData(games.data)
						}
						is Resource.Error -> {
							binding.progressBar.visibility = View.GONE
						}
					}
				}
			}

			with(binding.recyclerView) {
				layoutManager = LinearLayoutManager(context)
				setHasFixedSize(true)
				adapter = gameAdapter
			}
		}
	}

	override fun onDestroyView() {
		super.onDestroyView()
		_binding = null
	}
}