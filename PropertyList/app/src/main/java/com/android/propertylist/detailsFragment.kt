package com.android.propertylist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

class detailsFragment:Fragment(R.layout.details_page_fragment) {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var propertyLocation = arguments?.getString("propertyLocation")
        var propertyPrice = arguments?.getString("propertyPrice")

        var view = inflater.inflate(R.layout.details_page_fragment, container, false)

        view.findViewById<TextView>(R.id.propertyLocation).text=propertyLocation
        view.findViewById<TextView>(R.id.propertyPrice).text=propertyPrice

        view.findViewById<Button>(R.id.doneButton).setOnClickListener(){

            var parentFragmentManagerBeginTransaction=parentFragmentManager.beginTransaction()
            parentFragmentManagerBeginTransaction?.replace(R.id.fragmentContainer, listFragment())
            parentFragmentManagerBeginTransaction?.commit()
        }


        return view
    }
}