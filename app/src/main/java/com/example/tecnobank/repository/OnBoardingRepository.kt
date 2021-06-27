package com.example.tecnobank.repository

import android.content.SharedPreferences
import androidx.core.content.edit


private const val CHAVE_PASS = "Passou"

class OnBoardingRepository(private val preferences:SharedPreferences) {

    fun entrou() {
        preferences.edit {
            putBoolean(CHAVE_PASS, true)
        }
    }

    fun jaentrou(): Boolean = preferences.getBoolean(CHAVE_PASS, false)
}