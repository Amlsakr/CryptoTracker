package com.aml_sakr.cryptotracker.crypto.data.networking.dto

import com.aml_sakr.cryptotracker.crypto.domain.CoinPrice
import kotlinx.serialization.Serializable

@Serializable
data class CoinHistoryDto(
    val data: List<CoinPriceDto>
)