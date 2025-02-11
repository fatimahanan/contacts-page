package com.example.contactstask1.recyclerView

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.contactstask1.ContactsFragment
import com.example.contactstask1.R
import com.example.contactstask1.api.ContactsModel

class RecyclerViewAdapter(mainActivity: ContactsFragment) : RecyclerView.Adapter<RecyclerViewHolder>() {
    private var mClickListener: ItemClickListener = mainActivity

    private var contactsModelList = listOf<ContactsModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        Log.e("Adapter viewType", viewType.toString())
        val mContext = parent.context
        val layoutInflater = LayoutInflater.from(mContext)
        val view = layoutInflater.inflate(R.layout.recycler_view_item, parent, false)
        return RecyclerViewHolder(view, mClickListener)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        Log.e("Adapter position", position.toString())

        val newsModel = contactsModelList[position]

        holder.contactName.text = newsModel.name
        holder.contactCompany.text = newsModel.companyName
    }

    override fun getItemCount(): Int {
        return contactsModelList.size
    }

    fun updateData(contactsModel : List<ContactsModel>) {
        contactsModelList = contactsModel
        notifyDataSetChanged()
    }

    interface ItemClickListener {
        fun onItemClick(view: View?, position: Int)
    }
}