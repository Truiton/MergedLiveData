package com.truiton.mergedlivedata

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.truiton.mergedlivedata.databinding.FragmentDetailsBinding
import com.truiton.mergedlivedata.entity.Favourite

class DetailsFragment : Fragment() {
    private var _binding: FragmentDetailsBinding? = null

    // This property is only valid between onCreateView and
// onDestroyView.
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mainActivityViewModel: MainActivityViewModel =
            ViewModelProvider(requireActivity())[MainActivityViewModel::class.java]
        val venue = mainActivityViewModel.getSelectedPlace()
        binding.btnFavorite.isChecked = venue.favourite.favourite
        binding.btnFavorite.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                mainActivityViewModel.insert(Favourite(venue.id, true))
            } else {
                mainActivityViewModel.insert(Favourite(venue.id, false))
            }
        }

        binding.header.text = venue.name
        binding.content.text = venue.location.address
        binding.content2.text = venue.id
        binding.content3.text = venue.referralId
        binding.content4.text = venue.beenHere.toString()
        binding.content5.text = venue.venueChains.toString()
        binding.content6.text = venue.categories.toString()
        binding.content7.text = venue.contact.toString()
        binding.content8.text = venue.stats.toString()


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = DetailsFragment()
    }
}
