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
import com.google.gson.JsonArray
import kotlinx.android.synthetic.main.fragment_cc_facility.*
import kotlinx.android.synthetic.main.fragment_protect_play.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL

/**
 * A simple [Fragment] subclass.
 */
class protectPlayFragment : Fragment() {

    var optionStr = ""
    var array1 = ArrayList<ccProtect>()
    var array2 = ArrayList<ccPlay>()
    lateinit var adapter:ppAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_protect_play, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        ppRcyView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)



        //init spinner
        val spinadapter = ArrayAdapter<String>(context!!, android.R.layout.simple_spinner_dropdown_item, ArrayList<String>())
        val cityNames = resources.getStringArray(R.array.protectionplay).toList()
        spinadapter.addAll(cityNames)
        ppspinner.adapter = spinadapter
        ppspinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
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
                        optionStr=cityNames[0]
                    }
                    else->{
                        optionStr=cityNames[position]
                    }
                }
            }

        }



        ppresultButton.setOnClickListener {
            //aws lambda 데이터 받아오기
            //dropdown 보호구열일때
            array1.clear()
            array2.clear()

            if(optionStr.equals("보호구역")){
                processProtect()
                aboutAdapter()

            }else{
                processPlay();
                aboutAdapter();
            }

        }

    }

    fun aboutAdapter(){
        if(optionStr.equals("보호구역"))
            adapter = ppAdapter(array1, array2, true)
        else
            adapter = ppAdapter(array1, array2, false)



        adapter.itemClickListener = object:ppAdapter.OnItemClickListener{
            override fun OnItemClick1(
                holder: ppAdapter.ppViewHolder,
                view: View,
                data: ccProtect,
                position: Int
            ) {
                //Toast.makeText(context, "상세한 아이템1", Toast.LENGTH_SHORT).show()
                ccProtectItem(data).show(fragmentManager!!, "ccPlay $position")
            }

            override fun OnItemClick2(
                holder: ppAdapter.ppViewHolder,
                view: View,
                data: ccPlay,
                position: Int
            ) {
                //Toast.makeText(context, "상세한 아이템2", Toast.LENGTH_SHORT).show()
                ccPlayItem(data).show(fragmentManager!!, "ccPlay $position")
            }
        }


        ppRcyView?.adapter = adapter
    }

    private fun processPlay(){
        val editQuery = ppEdit.text;
        var urlBuilder:StringBuilder = StringBuilder("https://1qwds6aaii.execute-api.us-east-1.amazonaws.com/ccPlay")
        urlBuilder.append("?pName=" + editQuery);

        var url:URL = URL(urlBuilder.toString());
        var conn: HttpURLConnection = url.openConnection() as HttpURLConnection;
        conn.setRequestMethod("GET");
        var rd:BufferedReader;
        if(conn.responseCode >= 200 && conn.responseCode <= 300){
            rd = BufferedReader(InputStreamReader(conn.inputStream));
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
        rd.close();
        conn.disconnect();
        var jsonData:JSONObject? = null;
        jsonData = JSONObject(sb.toString())
        if(jsonData.getJSONObject("response").getJSONObject("body").getJSONObject("items").has("item")){
            var result1 = jsonData.getJSONObject("response").getJSONObject("body").getJSONObject("items")
            var resultJsonArray: JSONArray;
            try{

                resultJsonArray = result1.getJSONArray("item")
            }catch (e:Exception) {
                //검색 결과가 한개인 경우
                resultJsonArray = JSONArray()
                resultJsonArray.put(result1.getJSONObject("item"))
            }

            for(i in 0..resultJsonArray.length() - 1){
                var pItem = JSONObject(resultJsonArray.get(i).toString())
                var item = Array(6){ i -> ""}

                var ppProtectStrings = arrayOf("ciName", "ciNaddr2", "ciRaddr1", "name1", "name4", "name21");

                for(j in 0..5){
                    if(pItem.has(ppProtectStrings[j])){
                        if(pItem.getJSONObject(ppProtectStrings[j]).has("_text"))
                            item[j] = pItem.getJSONObject(ppProtectStrings[j]).getString("_text")
                    }
                }
                array2.add(ccPlay(item[0], item[1], item[2], item[3], item[4], item[5]));

            }

        }else{
            Toast.makeText(context, "검색 결과 없음", Toast.LENGTH_SHORT).show();
        }

    }

    private fun processProtect(){
        val editQuery = ppEdit.text;
        var urlBuilder:StringBuilder = StringBuilder("https://1qwds6aaii.execute-api.us-east-1.amazonaws.com/ccProtect")/*URL*/
        urlBuilder.append("?pName=" + editQuery);

        var url: URL = URL(urlBuilder.toString());
        var conn: HttpURLConnection = url.openConnection() as HttpURLConnection;
        conn.setRequestMethod("GET");
        var rd: BufferedReader;
        if(conn.responseCode >=200 && conn.responseCode <= 300){
            rd = BufferedReader(InputStreamReader(conn.inputStream));
        }else{
            rd = BufferedReader(InputStreamReader(conn.errorStream))
        }
        var sb:StringBuilder = StringBuilder();
        var line:String?;

        line = rd.readLine();
        while(true){
            sb.append(line);
            line = rd.readLine()
            if(line == null)
                break;
        }

        rd.close();
        conn.disconnect();
        //System.out.println(sb.toString());
        var jsonData:JSONObject? = null;
        jsonData = JSONObject(sb.toString());

        var normalResult:Boolean = jsonData.getJSONObject("response").getJSONObject("header").has("resultMsg")

        if(normalResult){
            var result1 = jsonData.getJSONObject("response").getJSONObject("body").getJSONObject("items")


            var resultJsonArray: JSONArray;
            try{

                resultJsonArray = result1.getJSONArray("item")
            }catch (e:Exception){
                //검색 결과가 한개인 경우
                resultJsonArray = JSONArray()
                resultJsonArray.put(result1.getJSONObject("item"))
            }



            for(i in 0..resultJsonArray.length() - 1){
                var pItem = JSONObject(resultJsonArray.get(i).toString())
                System.out.println(pItem)

                var item = Array(13){ i -> ""}

                var ppProtectStrings = arrayOf("fcltyKnd", "trgetFcltyNm", "rdnmadr", "lnmadr", "latitude", "longitude", "institutionNm"
                , "cmptncPolcsttnNm", "cctvYn", "cctvNumber", "prtcareaRw", "referenceDate", "insttCode")

                for(j in 0..12){
                    if(pItem.has(ppProtectStrings[j])){
                        if(pItem.getJSONObject(ppProtectStrings[j]).has("_text"))
                            item[j] = pItem.getJSONObject(ppProtectStrings[j]).getString("_text")
                    }
                }

                array1.add(ccProtect(item[0], item[1], item[2], item[3], item[4], item[5], item[6], item[7], item[8], item[9], item[10], item[11], item[12]))

            }


        }else{
            Toast.makeText(context, "검색 결과 없음", Toast.LENGTH_SHORT).show()
        }
    }

}
