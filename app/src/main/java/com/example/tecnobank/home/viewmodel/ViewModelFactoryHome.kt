package com.example.tecnobank.home.viewmodel

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tecnobank.R
import com.example.tecnobank.home.data.remote.EndPointHome
import com.example.tecnobank.home.repository.ExtratoRepositoty
import com.example.tecnobank.home.repository.InicioRepository
import com.example.tecnobank.intro.data.local.SharedPreferenceServices

class ViewModelFactoryHome(private val context: Context): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass == InicioViewModel::class.java) {
            return providerInicioViewModel() as T
        }
        if (modelClass == ExtratoViewModel::class.java) {
            return providerExtratoViewModel() as T
        }
        throw Exception("ViewModel não encotrado")
    }

    private fun providerExtratoViewModel(): ExtratoViewModel {
        return ExtratoViewModel(
            ExtratoRepositoty(
                providerRetrofitInstance(),
                providerSharedPreferenceService(providerSharedPreference())
            )
        )
    }

    private fun providerInicioViewModel(): InicioViewModel {
        return InicioViewModel(
            InicioRepository(
                providerRetrofitInstance(),
                providerSharedPreferenceService(providerSharedPreference())
            )
        )
    }

    private fun providerSharedPreferenceService(preferences: SharedPreferences): SharedPreferenceServices {
        return SharedPreferenceServices(preferences)
    }


    private fun providerRetrofitInstance():EndPointHome {
        return EndPointHome.getRetrofitInstance()
    }

    private fun providerSharedPreference(): SharedPreferences {
        return context.getSharedPreferences(
            R.string.preference_file_key.toString(), Context.MODE_PRIVATE
        )
    }

}
