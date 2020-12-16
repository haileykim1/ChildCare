package com.example.childcare

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.InflateException
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.lifecycle.Transformations.map
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.ccitem.*

/**
 * A simple [Fragment] subclass.
 */
class ccFacItem(val data:ccFacility) : AppCompatDialogFragment() {

    companion object{
        lateinit var myView:View
    }
    lateinit var googlemap: GoogleMap

    lateinit var loc: LatLng




    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        System.out.println("액티비티")
        itemName.text = data.KIDGARTN_NM
        itemDes.text = data.toString()
        /*if((data.REFINE_WGS84_LAT != null) && (data.REFINE_WGS84_LOGT != null)){
            loc = LatLng(data.REFINE_WGS84_LAT.toDouble(), data.REFINE_WGS84_LOGT.toDouble())
        }
*/
        itemCloseBtn.setOnClickListener {
            this.dialog?.dismiss()
        }

        //initmap
        /*val mapFragment = fragmentManager?.findFragmentById(com.google.android.gms.maps.R.id.map) as SupportMapFragment
        mapFragment.getMapAsync{
            googlemap = it

            googlemap.animateCamera(CameraUpdateFactory.newLatLngZoom(loc, 16.toFloat()))

            val options = MarkerOptions()
            options.position(loc)
            options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
            options.title(data.KIDGARTN_NM)
            val mk1 = googlemap.addMarker(options)
            mk1.showInfoWindow()

        }*/

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        System.out.println("뷰")
        if(myView != null){
            try{
                var parent:ViewGroup = myView.parent as ViewGroup
                parent?.removeView(myView)
            }catch(e:Exception){

            }
        }

        return myView
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        System.out.println("다이얼로그")
        try{
            myView = activity!!.layoutInflater.inflate(
                R.layout.ccitem,
                LinearLayout(activity),
                false
            )
        }catch (e: InflateException){
            //맵존재
            return AlertDialog.Builder(activity!!)
                .setView(myView)
                .create()
        }
        myView = activity!!.layoutInflater.inflate(
            R.layout.ccitem,
            LinearLayout(activity),
            false
        )
        //dialog 빌드
        return AlertDialog.Builder(activity!!)
            .setView(myView)
            .create()
    }


}
