package com.aml_sakr.cryptotracker.crypto.data.mappers

import com.aml_sakr.cryptotracker.crypto.data.networking.dto.CoinDto
import com.aml_sakr.cryptotracker.crypto.data.networking.dto.CoinPriceDto
import com.aml_sakr.cryptotracker.crypto.domain.Coin
import com.aml_sakr.cryptotracker.crypto.domain.CoinPrice
import java.time.Instant
import java.time.ZoneId

fun CoinDto.toCoin(): Coin = Coin(
    id = id,
    rank = rank,
    symbol = symbol,
    name = name,
    marketCapUsd = marketCapUsd,
    priceUsd = priceUsd,
    changePercent24Hr = changePercent24Hr ?: 0.0
)

fun CoinPriceDto.toCoinPrice(): CoinPrice = CoinPrice(
    priceUsd = priceUsd,
    dateTime = Instant.ofEpochMilli(time).atZone(ZoneId.systemDefault())
)