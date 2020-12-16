package com.example.childcare

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.dynamic.SupportFragmentWrapper
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions

/**
 * A simple [Fragment] subclass.
 */
class ccLocationFragment : Fragment() , OnMapReadyCallback {

    lateinit var mMap:GoogleMap;
    var userLat:Double = 0.0
    var userLon:Double = 0.0
    lateinit var myMarker:Marker
    var isClicked:Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val locView:View = inflater.inflate(R.layout.fragment_cc_location, container, false)

        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment

        mapFragment.getMapAsync(this)

        return locView
    }


    override fun onMapReady(p0: GoogleMap?) {
        mMap = p0!!


        System.out.println("디폴트 적용")
        val defLoc:LatLng = LatLng(37.382, 127.128)
        mMap.moveCamera(CameraUpdateFactory.newLatLng(defLoc))

        mMap.animateCamera(CameraUpdateFactory.zoomTo(15.toFloat()))

        mMap.setOnMapClickListener(GoogleMap.OnMapClickListener {
            System.out.println("터치")


            var mOptions:MarkerOptions = MarkerOptions()
            mOptions.title("myMark")
            userLat = it.latitude
            userLon = it.longitude

            mOptions.snippet("myMark")

            mOptions.position(LatLng(userLat, userLon))

            if(!isClicked){
                myMarker = mMap.addMarker(mOptions)
                isClicked = true
            }else{
                myMarker.position = mOptions.position
            }


        })



    }

}
