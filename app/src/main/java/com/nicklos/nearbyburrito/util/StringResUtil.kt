package com.nicklos.nearbyburrito.util

import android.content.res.Resources
import com.nicklos.nearbyburrito.R

fun Resources.getPricingString(priceLevel: Int): String =
        if (priceLevel < 0) getString(R.string.pricing_unknown)
        else getStringArray(R.array.pricing)[priceLevel]

fun Resources.getRatingString(rating: Float): String =
        if (rating < 0) getString(R.string.star_rating_unknown)
        else getString(R.string.star_rating, String.format("%.1f", rating))