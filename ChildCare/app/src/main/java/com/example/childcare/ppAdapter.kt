package com.example.childcare

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ppAdapter(val item1:ArrayList<ccProtect>, val item2:ArrayList<ccPlay>, val isProtect:Boolean)
    : RecyclerView.Adapter<ppAdapter.ppViewHolder>(){

    interface OnItemClickListener{
        fun OnItemClick1(holder:ppViewHolder, view: View, data : ccProtect, position:Int)

        fun OnItemClick2(holder:ppViewHolder, view:View, data:ccPlay, position:Int)

    }

    var itemClickListener:OnItemClickListener?= null

    inner class ppViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView){
        var itemName : TextView = itemView.findViewById(R.id.itemName)
        var itemDes : TextView = itemView.findViewById(R.id.itemDes)

        init{
            itemView.setOnClickListener {
                if(isProtect){
                    itemClickListener?.OnItemClick1(this,it,  item1[adapterPosition], adapterPosition)
                }else{
                    itemClickListener?.OnItemClick2(this, it, item2[adapterPosition], adapterPosition)
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ppViewHolder {
        val v:View = LayoutInflater.from(parent.context).inflate(R.layout.row, parent, false)
        return ppViewHolder(v)
    }

    override fun getItemCount(): Int {
        if(isProtect){
            return item1.size
        }else{
            return item2.size
        }
    }

    override fun onBindViewHolder(holder: ppViewHolder, position: Int) {
        if(isProtect){
            holder.itemName.text = item1[position].facTargetName
            holder.itemDes.text = item1[position].roadAddr

            if(holder.itemDes.text.equals(""))
                holder.itemDes.text = item1[position].nameAddr

        }else{
            holder.itemName.text = "이름"
            holder.itemDes.text = "설명"

        }
    }

}