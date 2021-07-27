package com.example.tecnobank.data.remote.model.pix

data class PixItensRequest(
    val email: String,
    val type: String,
    val description: String,
    val value: Double,
    val date: String
)