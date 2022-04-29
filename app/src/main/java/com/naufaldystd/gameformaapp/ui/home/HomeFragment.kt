package com.naufaldystd.gameformaapp.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.naufaldystd.core.data.Resource
import com.naufaldystd.core.ui.GameAdapter
import com.naufaldystd.gameformaapp.databinding.FragmentHomeBinding
import com.naufaldystd.gameformaapp.ui.detail.DetailGameActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

	private var _binding: FragmentHomeBinding? = null

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
			gameAdapter.onItemClick = { intentData ->
				val intent = Intent(activity, DetailGameActivity::class.java)
				intent.putExtra(DetailGameActivity.DATA, intentData)
				startActivity(intent)
			}

			homeViewModel.games.observe(viewLifecycleOwner) { games ->
				if (games != null) {
					when (games) {
						is Resource.Loading -> binding.loadingAnimation.visibility = View.VISIBLE
						is Resource.Success -> {
							binding.loadingAnimation.visibility = View.GONE
							gameAdapter.setData(games.data)
						}
						is Resource.Error -> {
							Toast.makeText(context, "error ${games.message}", Toast.LENGTH_SHORT).show()
							binding.loadingAnimation.visibility = View.GONE
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