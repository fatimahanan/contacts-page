package com.example.contactstask1.api

import retrofit2.http.GET
import retrofit2.http.Query

interface ContactsAPI {
    @GET("users")
    suspend fun getContacts(): List<ContactsEntity>
}