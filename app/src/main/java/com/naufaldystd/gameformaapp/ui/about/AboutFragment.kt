package com.naufaldystd.gameformaapp.ui.about

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.naufaldystd.gameformaapp.databinding.FragmentAboutBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AboutFragment : Fragment() {

	private var _binding: FragmentAboutBinding? = null

	private val binding get() = _binding!!

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {

		_binding = FragmentAboutBinding.inflate(inflater, container, false)
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		binding.learnBtn.setOnClickListener {
			val url = "https://api.rawg.io/docs/"
			val intent = Intent(Intent.ACTION_VIEW)
			intent.data = Uri.parse(url)
			startActivity(intent)
		}
	}

	override fun onDestroyView() {
		super.onDestroyView()
		_binding = null
	}
}