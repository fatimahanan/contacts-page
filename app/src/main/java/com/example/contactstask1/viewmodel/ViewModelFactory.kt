package com.example.contactstask1.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.contactstask1.api.ContactsRepository
import com.example.contactstask1.room.ContactDao

class ViewModelFactory(private val contactDao: ContactDao, private val context: Context) : ViewModelProvider.Factory {
    override fun <T : androidx.lifecycle.ViewModel> create(
        modelClass: Class<T>,
        extras: CreationExtras
    ): T {
        return if (modelClass.isAssignableFrom(ViewModel::class.java)) {
            ViewModel(ContactsRepository(contactDao, context)) as T
        } else {
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}

