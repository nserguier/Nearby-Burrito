package com.nicklos.nearbyburrito.ui.home

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.location.places.Place
import com.nicklos.nearbyburrito.R
import com.nicklos.nearbyburrito.util.getPricingString
import com.nicklos.nearbyburrito.util.getRatingString
import kotlinx.android.synthetic.main.nearby_burrito_item.view.*

/**
 * Adapter for the home recycler view.
 * Populate recycler view with nearby burrito places.
 */
class HomeAdapter(private val onPlaceClick: (Place) -> Unit) : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

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
        holder.bind(nearbyPlaces[position], onPlaceClick)
    }

    class ViewHolder(private val rowView: View) : RecyclerView.ViewHolder(rowView) {

        fun bind(place: Place, onClick: (Place) -> Unit) = with(rowView) {
            place_name.text = place.name
            place_address.text = place.address
            place_pricing.text = resources.getPricingString(place.priceLevel)
            place_rating.text = resources.getRatingString(place.rating)

            rowView.setOnClickListener { onClick(place) }
        }
    }

    private fun ViewGroup.inflate(layoutRes: Int): View =
            LayoutInflater.from(context).inflate(layoutRes, this, false)
}