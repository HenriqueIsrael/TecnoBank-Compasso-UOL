package com.example.tecnobank.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tecnobank.R

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity)
        supportActionBar?.hide()
    }
}