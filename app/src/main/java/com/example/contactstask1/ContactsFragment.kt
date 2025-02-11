package com.example.contactstask1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.contactstask1.api.ContactsModel
import com.example.contactstask1.recyclerView.RecyclerViewAdapter
import com.example.contactstask1.viewmodel.ViewModel

class ContactsFragment : Fragment(), RecyclerViewAdapter.ItemClickListener {

    private lateinit var mRecyclerView: RecyclerView
    private var mAdapter: RecyclerViewAdapter = RecyclerViewAdapter(this)
    private val viewModel by viewModels<ViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel.getContactsInfo()
        viewModel.contactsDetails.observe(viewLifecycleOwner) {
            mAdapter.updateData(it)
        }
        return inflater.inflate(R.layout.fragment_contacts, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mRecyclerView = view.findViewById(R.id.recyclerview)

        mRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            addItemDecoration(
                DividerItemDecoration(
                    mRecyclerView.context,
                    DividerItemDecoration.VERTICAL
                )
            )
            adapter = mAdapter
        }

    }


    override fun onItemClick(view: View?, position: Int) {

    }
}