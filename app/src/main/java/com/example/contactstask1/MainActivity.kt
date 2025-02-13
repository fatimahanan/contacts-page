package com.example.contactstask1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.setDisplayShowHomeEnabled(false);
        supportActionBar?.hide()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, ContactsFragment())
            .commit()

    }
}