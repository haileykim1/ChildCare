package com.example.childcare

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.fragment_cc_facility.*
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

/**
 * A simple [Fragment] subclass.
 */
class ccFacilityFragment : Fragment() {

    var cityStr = ""
    var array1 = ArrayList<ccFacility>()
    var array2 = ArrayList<ccFacility>()
    lateinit var adapter:ccFacAdapter
    var gson: Gson = Gson();

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cc_facility, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        ccFacRcyView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false);

        //init spinner
        val spinadapter = ArrayAdapter<String>(context!!, android.R.layout.simple_spinner_dropdown_item, ArrayList<String>())
        val cityNames = resources.getStringArray(R.array.gyungkiSigun).toList()
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
                        cityStr=cityNames[position]
                    }
                    else->{
                        cityStr=cityNames[position]
                    }
                }
            }

        }

        ccFacresultButton.setOnClickListener {
            array1.clear()
            array2.clear()

            processFac()
            aboutAdapter()
        }

    }
    private fun processFac(){
        var urlBuilder:StringBuilder = StringBuilder("https://1qwds6aaii.execute-api.us-east-1.amazonaws.com/ccFacility");
        urlBuilder.append("?pCity=" + cityStr);

        var url: URL = URL(urlBuilder.toString());
        var conn: HttpURLConnection = url.openConnection() as HttpURLConnection;
        conn.setRequestMethod("GET");
        var rd: BufferedReader;

        if(conn.responseCode >= 200 && conn.responseCode <= 300){
            //rd = BufferedReader(InputStreamReader(conn.inputStream));
            rd = conn.inputStream.bufferedReader()
        }else{
            rd = BufferedReader(InputStreamReader(conn.errorStream));
        }
        var sb:StringBuilder = StringBuilder();
        var line:String?;

        line = rd.readLine();
        while(true){
            sb.append(line);
            line = rd.readLine();
            if(line == null)
                break;
        }
        var jsonData: JSONObject? = null;
        jsonData = JSONObject(sb.toString())
        var json1 = jsonData.getJSONArray("ChildHouse");
        var jsonBase = JSONObject(json1.get(1).toString()).getJSONArray("row")


        val myType = object: TypeToken<ArrayList<ccFacility>>(){}.type;
        array1 = gson.fromJson(jsonBase.toString(), myType)
        System.out.println(array1.size);

        rd.close();
        conn.disconnect();

        searchQuery()
    }

    fun aboutAdapter(){
        adapter = ccFacAdapter(array2)

        adapter.itemClickListener = object:ccFacAdapter.OnItemClickListener{
            override fun OnItemClick(
                holder: ccFacAdapter.ccFacViewHolder,
                view: View,
                data: ccFacility,
                position: Int
            ) {
                //Toast.makeText(context, "상세 정보 출력", Toast.LENGTH_SHORT).show()
                ccFacItem(data).show(fragmentManager!!, "ccFacItem $position")
            }
        }

        ccFacRcyView?.adapter = adapter

    }
    private fun searchQuery(){
        val queryName:String = ccFacEdit.text.toString()
        for(item in array1){
            if(item.KIDGARTN_NM?.contains(queryName)!!){
                array2.add(item)
            }
        }
        if(array2.size == 0)
            Toast.makeText(context, "검색 결과 없음", Toast.LENGTH_SHORT).show()
    }

}
