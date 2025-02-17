package com.example.contactstask1.api

import android.content.Context
import android.util.Log
import com.example.contactstask1.room.ContactDao
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.contactstask1.room.Contact


class ContactsRepository(private val contactDao: ContactDao, private val context:Context) {

    //from API
    suspend fun getContactsInfoFromApi(): List<Contact> {
        val api: ContactsAPI = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants.BASE_URL) // Base URL
            .build()
            .create(ContactsAPI::class.java)
        try {
            val response = api.getContacts()
            // Log the response to check if API call is successful
            Log.d("API Response", response.toString())

            return response.map { contactEntity ->
                contactEntity.toContact()
            }
        } catch (e: Exception) {
            // Log the error if the API call fails
            Log.e("API Error", "Error fetching contacts from API: ${e.message}")
            throw e // Rethrow the exception to handle it properly in the calling code
        }
    }

//        val response = api.getContacts()
//        return response.map { contactEntity ->
//            contactEntity.toContact()

//            ContactsModel(
//                name = contactEntity.name,
//                companyName = contactEntity.company.name
//            )

    suspend fun getContactsInfo(): List<ContactsModel> {
        return if (NetworkUtil.isNetworkAvailable(context)) {
            val apiContacts = getContactsInfoFromApi()
            contactDao.deleteAll()  // Delete all existing contacts
            contactDao.insertAll(apiContacts) //save to room db
            apiContacts.map { it.toContactsModel() }  //return the ContactsModel
        } else {
            //if offline, get from room db
            contactDao.getAllContacts().map { it.toContactsModel() }
        }
    }
}