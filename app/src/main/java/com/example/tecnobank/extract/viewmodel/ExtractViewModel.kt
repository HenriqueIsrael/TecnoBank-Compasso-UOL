package com.example.tecnobank.extract.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tecnobank.data.remote.model.extract.ExtractResponse
import com.example.tecnobank.extract.repository.ExtractRepositoty
import kotlinx.coroutines.launch

class ExtractViewModel(private val extractRepository: ExtractRepositoty) : ViewModel() {

    private val _responseSucess = MutableLiveData<ExtractResponse>()
    val responseSucess: LiveData<ExtractResponse> = _responseSucess

    private val _responseErro = MutableLiveData<String>()
    val responseErro: LiveData<String> = _responseErro

    fun onOpenExtract(dataFilterStart: String, dataFilterEnd: String) {
        viewModelScope.launch {
            try {
                _responseSucess.postValue(
                    extractRepository.extractTransactions(
                        dataFilterStart,
                        dataFilterEnd
                    )
                )
            } catch (e: Exception) {
                _responseErro.postValue(e.message)
            }
        }
    }

}