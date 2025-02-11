package com.example.contactstask1.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.contactstask1.api.ContactsModel
import com.example.contactstask1.api.ContactsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ViewModel : ViewModel() {

    private val _contactsDetails = MutableLiveData<List<ContactsModel>>(emptyList())
    val contactsDetails: LiveData<List<ContactsModel>> = _contactsDetails
    private var job: Job? = null

    private val repository=ContactsRepository()

    fun getContactsInfo() {
        job = viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.getContactsInfo()
                withContext(Dispatchers.Main) {
                    _contactsDetails.value = response
                }
            }
            catch(e: Exception){
                withContext(Dispatchers.Main) {
                    Log.e("ViewModel", "Error fetching contacts: ${e.message}")
                }
            }
        }
    }
}