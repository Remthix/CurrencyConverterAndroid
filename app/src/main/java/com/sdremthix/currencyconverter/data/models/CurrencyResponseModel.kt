package com.sdremthix.currencyconverter.data.models

/**
 * The raw API response data holder.
 */
data class CurrencyResponseModel(
    val success: Boolean,
    val timestamp: Long,
    val base: String,
    val date: String,
    val rates: Map<String, Double>
)