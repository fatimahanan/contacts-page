package com.example.contactstask1.api

import com.example.contactstask1.room.Contact

//contacts entity (Api) to room db entity
fun ContactsEntity.toContact(): Contact {
    return Contact(
        id = this.id,
        name = this.name,
        companyName = this.company.name
    )
}
