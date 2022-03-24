package com.android.propertylist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() , PropertyListRecycleViewAdapter.OnItemClickListener{

    var propertyList: ArrayList<propertyModel> = ArrayList<propertyModel>();

    fun setUpPropertyList(){
        var propertyAddresses: Array<String> = resources.getStringArray(R.array.propertyAddresses);
        var propertyPrices: Array<out String> = resources.getStringArray(R.array.propertyPrices);
        var index: Int=0;
        for(x in propertyAddresses){
            propertyList.add( propertyModel(propertyLocation = x, propertyPrice =  propertyPrices[index].toInt()));
            index++
        }
    }
    var adapter:PropertyListRecycleViewAdapter = PropertyListRecycleViewAdapter(context = this, propertyList = propertyList, this);

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var recyclerView : RecyclerView = findViewById(R.id.mRecyclerView);

        setUpPropertyList();

        recyclerView.adapter = adapter;
        recyclerView.layoutManager=LinearLayoutManager(this);
        recyclerView.setHasFixedSize(true);
    }

    override fun onItemClick(postion: Int) {
        Toast.makeText(this, "Item $postion", Toast.LENGTH_LONG).show();
        val clickedItem = propertyList[postion];
        clickedItem.propertyPrice=0;
        adapter.notifyItemChanged(postion);
    }
}