package com.android.propertylist

import android.graphics.Bitmap

class propertyModel(var propertyLocation: String, var propertyPrice: Int, var propertyAgent: String, val propertyImage: Bitmap) {
   fun geLocation(): String{
        return propertyLocation;
    }

    fun getPrice(): Int{
        return propertyPrice;
    }
    fun getAgent(): String{
        return propertyAgent;
    }
}