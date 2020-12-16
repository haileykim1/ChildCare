package com.example.childcare

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatDialogFragment
import kotlinx.android.synthetic.main.fragment_cc_vacc_item.*

/**
 * A simple [Fragment] subclass.
 */
class ccVaccItem(val data:ccVaccination) : AppCompatDialogFragment() {

    companion object{
        lateinit var myView:View
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return myView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        itemName.text = data.FACLT_NM
        itemDes.text = data.toString()

        itemCloseBtn.setOnClickListener {
            this.dialog?.dismiss()
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        myView = activity!!.layoutInflater.inflate(
            R.layout.fragment_cc_vacc_item,
            LinearLayout(activity),
            false
        )
        return AlertDialog.Builder(activity!!)
            .setView(myView)
            .create()
    }

}
