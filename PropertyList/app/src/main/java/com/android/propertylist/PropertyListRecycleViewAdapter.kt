package com.android.propertylist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PropertyListRecycleViewAdapter( var inflaterr: LayoutInflater, var propertyList: ArrayList<propertyModel>,val listener: OnItemClickListener) : RecyclerView.Adapter<PropertyListRecycleViewAdapter.ViewHolder>() {



    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PropertyListRecycleViewAdapter.ViewHolder {
//        var inflater : LayoutInflater = LayoutInflater.from(context);
        var inflater : LayoutInflater = inflaterr;
        var view : View = inflater.inflate(R.layout.recycler_view_row, parent, false);

        return PropertyListRecycleViewAdapter.ViewHolder(view, listener);
    }

    override fun onBindViewHolder(
        holder: PropertyListRecycleViewAdapter.ViewHolder,
        position: Int
    ) {
        holder.properyAddressTextViewInViewHolder.text=propertyList.get(position).propertyLocation;
        holder.properyPriceTextViewInViewHolder.text="$"+propertyList.get(position).propertyPrice.toString()
    }


    override fun getItemCount(): Int {
        return  propertyList.size;
    }

    class ViewHolder(ItemView: View, var listenerr: OnItemClickListener) : RecyclerView.ViewHolder(ItemView) ,
    View.OnClickListener{
        val properyAddressTextViewInViewHolder: TextView = itemView.findViewById(R.id.propertyNameTextView)
        val properyPriceTextViewInViewHolder: TextView = itemView.findViewById(R.id.propertyPriceTextView)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            val postion: Int= adapterPosition;
            if(postion!=RecyclerView.NO_POSITION){
                listenerr.onItemClick(postion);
            }
        }
    }

    interface OnItemClickListener{
        fun onItemClick(postion: Int);
    }
}