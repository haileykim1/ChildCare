package com.example.childcare

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

class vaccAdapter(val items:ArrayList<ccVaccination>)
    : RecyclerView.Adapter<vaccAdapter.vaccViewHolder>(){

    interface OnItemClickListener{
        fun OnItemClick(holder:vaccViewHolder, view: View, data: ccVaccination, position: Int)
    }

    var itemClickListener: OnItemClickListener?= null

    inner class vaccViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView){
        var rowName: TextView = itemView.findViewById(R.id.itemName)
        var rowDes: TextView = itemView.findViewById(R.id.itemDes)

        init{
            itemView.setOnClickListener {
                itemClickListener?.OnItemClick(this, it, items[adapterPosition], adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): vaccViewHolder {
        val v:View = LayoutInflater.from(parent.context).inflate(R.layout.row, parent, false)
        return vaccViewHolder(v)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: vaccViewHolder, position: Int) {
        holder.rowName.text = items[position].FACLT_NM
        holder.rowDes.text = items[position].REFINE_ROADNM_ADDR
        if(holder.rowDes.text.equals(""))
            holder.rowDes.text = items[position].REFINE_LOTNO_ADDR
    }


}