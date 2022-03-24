package com.android.propertylist

class propertyModel(var propertyLocation: String, var propertyPrice: Int) {
   fun geLocation(): String{
        return propertyLocation;
    }

    fun getPrice(): Int{
        return propertyPrice;
    }
}