package com.truiton.mergedlivedata

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.truiton.mergedlivedata.entity.Favourite
import kotlinx.android.synthetic.main.fragment_details.*
import kotlinx.android.synthetic.main.fragment_details.view.*

class DetailsFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mainActivityViewModel: MainActivityViewModel = ViewModelProviders.of(activity!!).get(MainActivityViewModel::class.java)
        val venue = mainActivityViewModel.getSelectedPlace()
        view.btn_favorite.isChecked = venue.favourite.favourite
        view.btn_favorite.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                mainActivityViewModel.insert(Favourite(venue.id, true))
            } else {
                mainActivityViewModel.insert(Favourite(venue.id, false))
            }
        }

        header.text = venue.name
        content.text = venue.location.address
        content2.text = venue.id
        content3.text = venue.referralId
        content4.text = venue.beenHere.toString()
        content5.text = venue.venueChains.toString()
        content6.text = venue.categories.toString()
        content7.text = venue.contact.toString()
        content8.text = venue.stats.toString()


    }

    companion object {
        @JvmStatic
        fun newInstance() = DetailsFragment()
    }
}
