package com.example.contactstask1.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.contactstask1.api.ContactsModel

@Entity(tableName = "contacts")
data class Contact(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "company_name") val companyName: String
){
    fun toContactsModel(): ContactsModel{           //to convert contactsEntity(room db) to contact obj
        return ContactsModel(
            name=this.name,
            companyName=this.companyName
        )
    }
}

