package com.nicklos.nearbyburrito.ui.home

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.location.places.Place
import com.nicklos.nearbyburrito.R
import kotlinx.android.synthetic.main.nearby_burrito_item.view.*

/**
 * Adapter for the home recycler view.
 * Populate recycler view with nearby burrito places.
 */
class HomeAdapter : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    private val nearbyPlaces = mutableListOf<Place>()

    fun setList(places: List<Place>) {
        nearbyPlaces.clear()
        nearbyPlaces.addAll(places)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ViewHolder(parent.inflate(R.layout.nearby_burrito_item))

    override fun getItemCount() = nearbyPlaces.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(nearbyPlaces[position])
    }

    class ViewHolder(private val rowView: View) : RecyclerView.ViewHolder(rowView) {

        fun bind(place: Place) = with(rowView) {
            place_name.text = place.name
            place_address.text = place.address

            val priceLevel = place.priceLevel
            place_pricing.text =
                    if (priceLevel < 0) resources.getString(R.string.pricing_unknown)
                    else resources.getStringArray(R.array.pricing)[priceLevel]

            val rating = place.rating
            place_rating.text =
                    if (rating < 0) resources.getString(R.string.star_rating_unknown)
                    else resources.getString(R.string.star_rating, String.format("%.1f", rating))
        }
    }

    private fun ViewGroup.inflate(layoutRes: Int): View =
            LayoutInflater.from(context).inflate(layoutRes, this, false)
}