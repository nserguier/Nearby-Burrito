package com.nicklos.nearbyburrito.ui

import android.databinding.BindingAdapter
import android.widget.TextView
import com.nicklos.nearbyburrito.util.getPricingString
import com.nicklos.nearbyburrito.util.getRatingString

@BindingAdapter("pricing")
fun setPricing(textView: TextView, pricing: Int?) {
    pricing ?: return
    with(textView) {
        text = resources.getPricingString(pricing)
    }
}

@BindingAdapter("rating")
fun setRating(textView: TextView, rating: Float?) {
    rating ?: return
    with(textView) {
        text = resources.getRatingString(rating)
    }
}