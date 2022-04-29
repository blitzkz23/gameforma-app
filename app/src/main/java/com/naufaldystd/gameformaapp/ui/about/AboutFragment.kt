package com.naufaldystd.gameformaapp.ui.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.naufaldystd.gameformaapp.databinding.FragmentSlideshowBinding

class AboutFragment : Fragment() {

	private var _binding: FragmentSlideshowBinding? = null

	// This property is only valid between onCreateView and
	// onDestroyView.
	private val binding get() = _binding!!

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		val slideshowViewModel =
			ViewModelProvider(this).get(AboutViewModel::class.java)

		_binding = FragmentSlideshowBinding.inflate(inflater, container, false)
		return binding.root
	}

	override fun onDestroyView() {
		super.onDestroyView()
		_binding = null
	}
}