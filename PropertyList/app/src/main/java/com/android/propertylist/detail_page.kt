package com.android.propertylist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class detail_page : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_page)

        val actionBar = supportActionBar
            actionBar!!.title="Details Page"
        actionBar!!.setDisplayHomeAsUpEnabled(true)
    }
}