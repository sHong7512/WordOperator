package com.shong.wordoperator

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NameAdapter internal constructor(
    context: Context,
    val nameList: List<String>
) :
    RecyclerView.Adapter<NameAdapter.ViewHolder>() {
    private val TAG = this::class.java.simpleName + "_sHong"

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private val wordOperator = WordOperator()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.nameText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : ViewHolder {
        val itemView = inflater.inflate(R.layout.item_name, parent, false)

        return ViewHolder(itemView)
    }

    private var selectedPosition = 0
    override fun onBindViewHolder(holder: ViewHolder, @SuppressLint("RecyclerView") position: Int) {
        holder.apply {
            nameTextView.text = nameList[position]

            if(selectedPosition == position) nameTextView.setBackgroundColor(Color.parseColor("#ffeecc"))
            else nameTextView.setBackgroundColor(Color.parseColor("#ffffff"))
        }
    }

    override fun getItemCount() = nameList.size

    internal fun getPositionInitalChs(str: String) : Int{
        val strIndex = wordOperator.initialChs.indexOf(str)
        var position = 0
        for(name in nameList){
            val firstName = name.substring(0,1)
            Log.d(TAG, "$strIndex $${wordOperator.getInitial(firstName)}")
            if(strIndex <= wordOperator.getInitial(firstName).index){
                position = nameList.indexOf(name)
                break
            }
        }
        return position
    }

    internal fun setSelectedPosition(position: Int){
        selectedPosition = position
        notifyDataSetChanged()
    }

}