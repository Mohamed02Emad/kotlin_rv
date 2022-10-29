package com.example.android.kotlinrv.recyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android.kotlinrv.R

class AdapterRV(private val arrayList: ArrayList<NoteDataClass>) :
    RecyclerView.Adapter<AdapterRV.ViewHolderRV>() {

    private lateinit var myInterface: RecyclerInterface

    class ViewHolderRV(itemView: View, recyclerInterface: RecyclerInterface) :
        RecyclerView.ViewHolder(itemView) {
        val colorImg: ImageView = itemView.findViewById(R.id.img)
        val title: TextView = itemView.findViewById(R.id.title)
        val content: TextView = itemView.findViewById(R.id.content)

        init {
            itemView.setOnClickListener {
                recyclerInterface.onClick(position = adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderRV {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card, parent, false)
        return ViewHolderRV(view, myInterface)
    }

    override fun onBindViewHolder(holder: ViewHolderRV, position: Int) {
        val dataObject = arrayList[position]
        holder.colorImg.setBackgroundColor(dataObject.color)
        holder.content.text = dataObject.content
        holder.title.text = dataObject.title
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    fun setOnClicked(recyclerInterface: RecyclerInterface) {
        myInterface = recyclerInterface
    }

}