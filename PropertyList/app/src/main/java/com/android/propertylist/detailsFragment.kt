package com.android.propertylist

import android.app.AlertDialog
import android.content.DialogInterface
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import java.lang.Exception

class detailsFragment:Fragment(R.layout.details_page_fragment) {

    lateinit var locationEditText : EditText;
    lateinit var priceEditText: EditText;
    lateinit var agentEditText: EditText;

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //Getting required data from parent using arguments
        var propertyLocation = arguments?.getString("propertyLocation")
        var propertyPrice = arguments?.getString("propertyPrice")
        var propertyAgent = arguments?.getString("propertyAgent")
        var propertyImage = arguments?.getString("propertyImage")

        var view = inflater.inflate(R.layout.details_page_fragment, container, false)

        //initializing the EditTexts
        locationEditText = view.findViewById<EditText>(R.id.propertyLocation);
        priceEditText = view.findViewById<EditText>(R.id.propertyPrice);
        agentEditText = view.findViewById<EditText>(R.id.propertyAgent);

        //initialize imageView and set custom house image
        var imageView = view.findViewById<ImageView>(R.id.imageView)
        imageView.setImageBitmap(stringToBitmap(propertyImage.toString()))

        //Filling the edittexts with default data
        locationEditText.setText(propertyLocation)
        priceEditText.setText(propertyPrice)
        agentEditText.setText(propertyAgent)

        //On done button, check if any data has been updated or not and then return to the listFragment.
        view.findViewById<Button>(R.id.doneButton).setOnClickListener(){
            var location = locationEditText.text.toString().trim()
            var price = priceEditText.text.toString().trim()
            var agent = agentEditText.text.toString().trim()
            if(location == propertyLocation.toString().trim() && price == propertyPrice.toString().trim() && agent == propertyAgent.toString().trim()){
                returnToListPageFragment(false)
            }else{
                returnToListPageFragment(true)
            }
        }

        //Overriding the default behaviour of the on back button pressed function
        val callBack=object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                var location = locationEditText.text.toString().trim()
                var price = priceEditText.text.toString().trim()
                var agent = agentEditText.text.toString().trim()

//                check if any data has been updated or not and then return to the listFragment.
                if(location == propertyLocation.toString().trim() && price == propertyPrice.toString().trim() && agent == propertyAgent.toString().trim()){
                    returnToListPageFragment(false)
                }else{
                    val alertDialogBuilder = AlertDialog.Builder(inflater.context)
                    alertDialogBuilder.setTitle("Quit page??")
                    alertDialogBuilder.setMessage("Are you sure, you want to quit?\nQuitting now, will discard all unsaved changes!")
                    alertDialogBuilder.setPositiveButton("NO"){ dialogInterface: DialogInterface, i: Int ->
                        //Do nothing
                        //Close pop up
                    }
                    alertDialogBuilder.setNegativeButton("YES"){ dialogInterface: DialogInterface, i: Int -> returnToListPageFragment(false) }
                    var alertDialog: AlertDialog=alertDialogBuilder.create();
                    alertDialog.setCancelable(false);
                    alertDialog.show();
                }
            }
        }

        //add the on CallBack overridden function
        requireActivity().onBackPressedDispatcher.addCallback(callBack);

        return view;
    }

    // coverts bitMapString back to bitmap for imageview
    private fun stringToBitmap(string: String): Bitmap {
        var encodedByte = Base64.decode(string, Base64.DEFAULT)
        var bitmap = BitmapFactory.decodeByteArray(encodedByte, 0, encodedByte.size)
        return bitmap
    }

    //Function that triggers the returnToListFragment
    fun returnToListPageFragment(containsUpdatedData: Boolean){
        try{

            var location = locationEditText.text.toString().trim()
            var price = priceEditText.text.toString().trim().toInt()
            var agent = agentEditText.text.toString().trim()

            var listFragment = listFragment()
            var args = Bundle()
            args.putBoolean("containsUpdatedData", containsUpdatedData)

            //If contains updated data, pass the data to the ListFragment, else just push to ListFragment
            if(containsUpdatedData){
                args.putString("updatedPropertyLocation", location)
                args.putString("updatedPropertyPrice", price.toString())
                args.putString("updatedPropertyAgent", agent);

                var index= arguments?.getInt("index")
                if (index != null) {
                    args.putInt("index",index)
                };
            }

            listFragment.arguments=args;

            var parentFragmentManagerBeginTransaction=parentFragmentManager.beginTransaction()
            parentFragmentManagerBeginTransaction?.replace(R.id.fragmentContainer, listFragment)
            parentFragmentManagerBeginTransaction?.commit()
        }catch (e: Exception){
            val alertDialogBuilder = AlertDialog.Builder(context)
            alertDialogBuilder.setTitle("Error")
            alertDialogBuilder.setMessage("An error occurred. Please try checking the input again.")
            alertDialogBuilder.setNegativeButton("Close"){ dialogInterface: DialogInterface, i: Int ->
                //DO nothing
            }
            var alertDialog: AlertDialog=alertDialogBuilder.create();
            alertDialog.setCancelable(false);
            alertDialog.show();
        }
    }
}