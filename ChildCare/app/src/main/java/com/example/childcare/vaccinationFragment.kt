package com.example.childcare

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.fragment_vaccination.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.reflect.Type
import java.net.HttpURLConnection
import java.net.URL

/**
 * A simple [Fragment] subclass.
 */
class vaccinationFragment : Fragment() {

    var optionStr = ""
    var array1 = ArrayList<ccVaccination>()
    var array2 = ArrayList<ccVaccination>()
    lateinit var adapter:vaccAdapter
    var gson:Gson = Gson()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_vaccination, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        vaccRcyView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false);

        //init spinner
        val spinadapter = ArrayAdapter<String>(context!!, android.R.layout.simple_spinner_dropdown_item, ArrayList<String>())
        val cityNames = resources.getStringArray(R.array.gyungkiSigun).toList()
        spinadapter.addAll(cityNames)
        vaccspinner.adapter = spinadapter
        vaccspinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                optionStr = ""
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                when(position){
                    0->{
                        optionStr=cityNames[position]
                    }
                    else->{
                        optionStr=cityNames[position]
                    }
                }
            }

        }

        vaccresultButton.setOnClickListener {
            //aws lambda 데이터 받아오기
            array1.clear()
            array2.clear()

            processVacc()
            aboutAdapter()

        }
    }

    fun processVacc(){
        var urlBuilder:StringBuilder = StringBuilder("https://1qwds6aaii.execute-api.us-east-1.amazonaws.com/ccVaccination");
        urlBuilder.append("?pCity=" + optionStr);

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
        var json1 = jsonData.getJSONArray("TbChildnatnPrvntncltnmdnstM");
        var jsonBase = JSONObject(json1.get(1).toString()).getJSONArray("row")


        val myType = object: TypeToken<ArrayList<ccVaccination>>(){}.type;
        array1 = gson.fromJson(jsonBase.toString(), myType)
        //System.out.println(array1.size);

        rd.close();
        conn.disconnect();
        searchQuery()

    }

    fun aboutAdapter(){
        adapter = vaccAdapter(array2)

        adapter.itemClickListener = object:vaccAdapter.OnItemClickListener{
            override fun OnItemClick(
                holder: vaccAdapter.vaccViewHolder,
                view: View,
                data: ccVaccination,
                position: Int
            ) {
                //Toast.makeText(context, "상세 정보 출력", Toast.LENGTH_SHORT).show()
                ccVaccItem(data).show(fragmentManager!!, "vacc $position");
            }
        }

        vaccRcyView?.adapter = adapter
    }

    private fun searchQuery(){
        val queryName:String = vaccEdit.text.toString()
        for(item in array1){
            if(item.FACLT_NM?.contains(queryName)!!){
                array2.add(item)
            }
        }
        if(array2.size == 0)
            Toast.makeText(context, "검색 결과 없음", Toast.LENGTH_SHORT).show()
    }
}
