package com.android.propertylist

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(){

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
//    var adapter:PropertyListRecycleViewAdapter = PropertyListRecycleViewAdapter(context = this, propertyList = propertyList, this);

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainer, listFragment())
        fragmentTransaction.commit()


////
//        setUpPropertyList();
//        var recyclerView : RecyclerView = findViewById(R.id.mRecyclerView);
//        recyclerView.adapter = adapter;
//        recyclerView.layoutManager= LinearLayoutManager(this);
//        recyclerView.setHasFixedSize(true);

    }

//    override fun onItemClick(postion: Int) {
//
//        print("Pringting")
//        Toast.makeText(this, "Item $postion", Toast.LENGTH_LONG).show();
//        val clickedItem = propertyList[postion];
//        clickedItem.propertyPrice=0;
//        var inflater : LayoutInflater = LayoutInflater.from(this);
//
//        var adapter:PropertyListRecycleViewAdapter = PropertyListRecycleViewAdapter( inflaterr = inflater, propertyList = propertyList, this);
//
//        adapter.notifyItemChanged(postion);
//
//        replaceFragment(detailsFragment())
//
////        val intent = Intent(this, detail_page::class.java)
//////        intent.putExtra("key", value)
////        startActivity(intent)
//    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainer, fragment)
        fragmentTransaction.commit()
    }
}