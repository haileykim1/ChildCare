package com.example.childcare

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ccFacAdapter(var items:ArrayList<ccFacility>) : RecyclerView.Adapter<ccFacAdapter.ccFacViewHolder>(){

    interface OnItemClickListener{
        fun OnItemClick(holder:ccFacViewHolder, view: View, data: ccFacility, position: Int)
    }

    var itemClickListener: OnItemClickListener?= null

    inner class ccFacViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView){
        var rowName: TextView = itemView.findViewById(R.id.itemName)
        var rowDes: TextView = itemView.findViewById(R.id.itemDes)

        init{
            itemView.setOnClickListener {
                itemClickListener?.OnItemClick(this, it, items[adapterPosition], adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ccFacViewHolder {
        val v:View = LayoutInflater.from(parent.context).inflate(R.layout.row, parent, false)
        return ccFacViewHolder(v)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ccFacViewHolder, position: Int) {
        holder.rowName.text = items[position].KIDGARTN_NM
        holder.rowDes.text = items[position].REFINE_ROADNM_ADDR
    }


}