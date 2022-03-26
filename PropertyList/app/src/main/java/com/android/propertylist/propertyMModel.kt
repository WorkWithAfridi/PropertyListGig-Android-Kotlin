package com.android.propertylist

class propertyModel(var propertyLocation: String, var propertyPrice: Int, var propertyAgent: String) {
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