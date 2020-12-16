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
import kotlinx.android.synthetic.main.fragment_cc_play_item.*

/**
 * A simple [Fragment] subclass.
 */
class ccPlayItem(val data:ccPlay) : AppCompatDialogFragment() {

    lateinit var myView:View

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        itemName.text = data.playName
        itemDes.text = data.toString()

        itemCloseBtn.setOnClickListener {
            this.dialog?.dismiss()
        }

    }


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        myView = activity!!.layoutInflater.inflate(
            R.layout.fragment_cc_play_item,
            LinearLayout(activity),
            false
        )

        var builder: AlertDialog.Builder = AlertDialog.Builder(activity!!)

        builder.setView(myView)


        return builder.create()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        return myView
    }

}
