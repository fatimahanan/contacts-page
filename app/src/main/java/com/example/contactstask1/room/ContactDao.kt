package com.example.contactstask1.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ContactDao {
    @Insert
    suspend fun insertAll(contacts: List<Contact>)

    @Query("SELECT * FROM contacts")
    suspend fun getAllContacts(): List<Contact>

    @Delete
    suspend fun delete(contact: Contact)

    @Query("DELETE FROM contacts")
    suspend fun deleteAll()
}
