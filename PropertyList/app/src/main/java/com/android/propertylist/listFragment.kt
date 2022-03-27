package com.android.propertylist

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.io.ByteArrayOutputStream
import kotlin.random.Random

class listFragment : Fragment(R.layout.list_page_fragment), PropertyListRecycleViewAdapter.OnItemClickListener{

    private lateinit var recyclerView : RecyclerView;

    //An arraylist to store all the property classes, which will be generated from R.String array
    var propertyList: ArrayList<propertyModel> = ArrayList<propertyModel>();

    //generates random image for propertyModel
    private fun getRandomImage() : Bitmap{
//        val imageView: ImageView = view.findViewById(R.id.imageView)
        val bitmapForHouseOne= BitmapFactory.decodeResource(resources, R.drawable.house1)
        val bitmapForHouseTwo= BitmapFactory.decodeResource(resources, R.drawable.house2)
        val bitmapForHouseThree= BitmapFactory.decodeResource(resources, R.drawable.house2)
        val getRandomNumber = Random.nextInt(0, 2)
        if(getRandomNumber==0) return bitmapForHouseOne;
        if(getRandomNumber==1) return bitmapForHouseTwo;
        if(getRandomNumber==2) return bitmapForHouseThree;
        return bitmapForHouseOne
    }


    //Sets up propertyList
    private fun setUpPropertyList(){
        var propertyAddresses: Array<String> = resources.getStringArray(R.array.propertyAddresses);
        var propertyPrices: Array<out String> = resources.getStringArray(R.array.propertyPrices);
        var propertyAgents: Array<out String> = resources.getStringArray(R.array.propertyAgents);
        var index: Int=0;
        for(x in propertyAddresses){
            propertyList.add( propertyModel(propertyLocation = x, propertyPrice =  propertyPrices[index].toInt(), propertyAgent = propertyAgents[index], propertyImage = getRandomImage()));
            index++
        }
    }

    //Adapter for recycler view
    lateinit var adapter:PropertyListRecycleViewAdapter;

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setUpPropertyList();

        //checks if the parent passed in any updated value
        if(arguments?.getBoolean("containsUpdatedData") == true){
            var propertyLocation = arguments?.getString("updatedPropertyLocation")
            var propertyPrice = arguments?.getString("updatedPropertyPrice")
            var propertyAgent = arguments?.getString("updatedPropertyAgent")
            var index = arguments?.getInt("index")

            //Updated the required propertyList Class
            propertyList[index!!].propertyLocation= propertyLocation.toString()
            propertyList[index!!].propertyPrice= propertyPrice!!.toInt()
            propertyList[index!!].propertyAgent= propertyAgent.toString()
        }

        var view : View = inflater.inflate(R.layout.list_page_fragment, container, false)
        recyclerView=view.findViewById(R.id.mRecyclerView);
        recyclerView.setHasFixedSize(true);
        adapter= PropertyListRecycleViewAdapter( inflaterr = inflater, propertyList = propertyList, this);
        recyclerView.adapter=adapter;
        recyclerView.layoutManager=LinearLayoutManager(context);
        return view ;
    }

    override fun onItemClick(postion: Int) {
        val clickedItem = propertyList[postion];
        var detailFrag = detailsFragment();
        var args = Bundle()

        //passing the required data to the details page using arguments
        args.putString("propertyLocation", clickedItem.propertyLocation);
        args.putString("propertyPrice", clickedItem.propertyPrice.toString());
        args.putString("propertyAgent", clickedItem.propertyAgent.toString());
        args.putString("propertyImage", bitMapToString(clickedItem.propertyImage));
        args.putInt("index", postion);

        detailFrag.arguments=args

        var parentFragmentManagerBeginTransaction=parentFragmentManager.beginTransaction()
        parentFragmentManagerBeginTransaction?.replace(R.id.fragmentContainer, detailFrag)
        parentFragmentManagerBeginTransaction?.commit()
    }

    //Converts Bitmap to string to pass data to details fragment
    fun bitMapToString( bitmap: Bitmap): String{
        var boas = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, boas)
        val b = boas.toByteArray()
        var temp = Base64.encodeToString(b, Base64.DEFAULT)
        return  temp
    }

}