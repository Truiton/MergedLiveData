package com.truiton.mergedlivedata

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity(), SearchFragment.OnSearchFragmentInteractionListener {


    companion object {
        val TRIGGER_SERACH = 100
        val SEARCH_TRIGGER_DELAY_IN_MS = 300L
        val SEARCH_TAG = "search_tag"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fragment = supportFragmentManager.findFragmentByTag(SEARCH_TAG)
        if (fragment == null) {
            val ft = supportFragmentManager.beginTransaction()
            ft.replace(R.id.container, SearchFragment.newInstance(), SEARCH_TAG)
            ft.commit()
        }

        // For solving the empty room problem - so that we have some dat on screen initially to play with
        val mainActivityViewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]
        mainActivityViewModel.getPlaces("coffee")
    }


    override fun onItemClick(position: Int) {
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.container, DetailsFragment.newInstance())
        ft.addToBackStack(null)
        ft.commit()
    }
}
