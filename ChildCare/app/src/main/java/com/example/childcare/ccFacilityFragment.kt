package com.example.childcare

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.fragment_cc_facility.*

/**
 * A simple [Fragment] subclass.
 */
class ccFacilityFragment : Fragment() {

    var cityStr = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cc_facility, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //init spinner
        val spinadapter = ArrayAdapter<String>(context!!, android.R.layout.simple_spinner_dropdown_item, ArrayList<String>())
        val cityNames = resources.getStringArray(R.array.city).toList()
        spinadapter.addAll(cityNames)
        spinner.adapter = spinadapter
        spinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                cityStr = ""
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                when(position){
                    0->{
                        cityStr=""
                    }
                    else->{
                        cityStr=cityNames[position]
                    }
                }
            }

        }

    }
}
