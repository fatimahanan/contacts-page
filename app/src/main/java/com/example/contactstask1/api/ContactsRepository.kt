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
            val response = api.getContacts()

            return response.map { contactEntity ->
                contactEntity.toContact()
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
            contactDao.deleteAll()
            contactDao.insertAll(apiContacts)
            apiContacts.map { it.toContactsModel() }
        } else {
            //if offline, get from room db
            contactDao.getAllContacts().map { it.toContactsModel() }
        }
    }
}