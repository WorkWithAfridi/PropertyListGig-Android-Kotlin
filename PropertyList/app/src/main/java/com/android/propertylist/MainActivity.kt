package com.android.propertylist

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(R.layout.activity_main){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Creates a listFragment var, to pass in conditions using arguments
        var listFragment = listFragment()
        var args = Bundle()
        args.putBoolean("containsUpdatedData", false)

        listFragment.arguments=args

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        //setups the initial fragment
        fragmentTransaction.replace(R.id.fragmentContainer, listFragment)
        fragmentTransaction.commit()

    }
}