package com.truiton.mergedlivedata

import android.content.Context
import android.location.Location
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.ToggleButton
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.truiton.mergedlivedata.databinding.RecyclerviewItemBinding
import com.truiton.mergedlivedata.model.Venues


class FourSquareAdapter internal constructor(
    context: Context
) : RecyclerView.Adapter<FourSquareAdapter.PlaceViewHolder>() {
    private val ctx = context
    private val inflater: LayoutInflater = LayoutInflater.from(ctx)
    private var foursquareItems = emptyList<Venues>() // Cached copy of foursquareItems
    lateinit var mClickListener: ClickListener
    private lateinit var binding: RecyclerviewItemBinding

    inner class PlaceViewHolder(viewBinding: RecyclerviewItemBinding) :
        RecyclerView.ViewHolder(viewBinding.root),
        View.OnClickListener {
        init {
            viewBinding.root.setOnClickListener(this)
        }

        val placeName: TextView = viewBinding.placeName
        val placeCategory: TextView = viewBinding.placeCategory
        val placeDistance: TextView = viewBinding.placeDistance
        val placeIcon: ImageView = viewBinding.placeIcon
        val favouriteIcon: ToggleButton = viewBinding.buttonFavorite

        override fun onClick(v: View) {
            mClickListener.onClick(absoluteAdapterPosition, v)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceViewHolder {
        binding = RecyclerviewItemBinding.inflate(inflater, parent, false)
        return PlaceViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlaceViewHolder, position: Int) {
        val current = foursquareItems[position]
        holder.placeName.text = current.name
        if (current.categories.isNotEmpty()) {
            holder.placeCategory.text = current.categories[0].name
            Glide.with(ctx)
                .load(current.categories[0].icon.prefix + "bg_64" + current.categories[0].icon.suffix)
                .into(holder.placeIcon)
        } else {
            holder.placeCategory.text = "N/A"
            Glide.with(ctx).load("https://dummyimage.com/64x64/000/fff&text=NA")
                .into(holder.placeIcon)
        }
        holder.placeDistance.text =
            getDistanceInMeters(47.60621, -122.33207, current.location.lat, current.location.lat)
        holder.favouriteIcon.isChecked = current.favourite.favourite


    }

    internal fun setWords(venue: List<Venues>) {
        this.foursquareItems = venue
        notifyDataSetChanged()
    }

    override fun getItemCount() = foursquareItems.size

    fun setOnItemClickListener(aClickListener: ClickListener) {
        mClickListener = aClickListener
    }

    interface ClickListener {
        fun onClick(position: Int, view: View)
    }

    fun getDistanceInMeters(
        startLatitude: Double,
        startLongitude: Double,
        endLatitude: Double,
        endLongitude: Double
    ): String {
        val results = FloatArray(1)
        Location.distanceBetween(startLatitude, startLongitude, endLatitude, endLongitude, results)
        return results[0].toString() + " meters"
    }
}