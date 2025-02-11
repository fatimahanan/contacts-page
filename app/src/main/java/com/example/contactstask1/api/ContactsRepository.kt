package com.example.contactstask1.api

import android.provider.ContactsContract.Contacts
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ContactsRepository {
    suspend fun getContactsInfo(): List<ContactsModel> {
        val api: ContactsAPI = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants.BASE_URL) // Base URL
            .build()
            .create(ContactsAPI::class.java)
        val response = api.getContacts()
        return response.map { contactEntity ->
            ContactsModel(
                name = contactEntity.name,
                companyName = contactEntity.company.name
            )
        }
    }
}