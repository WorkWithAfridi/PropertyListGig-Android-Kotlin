package com.android.propertylist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class listFragment : Fragment(R.layout.list_page_fragment), PropertyListRecycleViewAdapter.OnItemClickListener{

    lateinit var recyclerView : RecyclerView;

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
//    var adapter:PropertyListRecycleViewAdapter = PropertyListRecycleViewAdapter(context = context, propertyList = propertyList, this);

    lateinit var adapter:PropertyListRecycleViewAdapter;

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setUpPropertyList();
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
        clickedItem.propertyPrice=0;
        adapter.notifyItemChanged(postion);


        var detailFrag = detailsFragment();
        var args = Bundle()
        args.putString("propertyLocation", clickedItem.propertyLocation);
        args.putString("propertyPrice", clickedItem.propertyPrice.toString());
        detailFrag.arguments=args

        var parentFragmentManagerBeginTransaction=parentFragmentManager.beginTransaction()
        parentFragmentManagerBeginTransaction?.replace(R.id.fragmentContainer, detailFrag)
        parentFragmentManagerBeginTransaction?.commit()
    }

}