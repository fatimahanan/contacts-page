package com.example.contactstask1.recyclerView

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.contactstask1.R

class RecyclerViewHolder(itemView: View, mClickListener: RecyclerViewAdapter.ItemClickListener) :
    RecyclerView.ViewHolder(itemView) {
    var contactName: TextView = itemView.findViewById<TextView>(R.id.contact_name)
    var contactCompany: TextView = itemView.findViewById<TextView>(R.id.company_name)

    init {
        itemView.setOnClickListener { view ->
            mClickListener.onItemClick(view, adapterPosition)
        }
    }
}