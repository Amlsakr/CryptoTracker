package com.aml_sakr.cryptotracker.crypto.data.networking

import com.aml_sakr.cryptotracker.core.data.networking.constructUrl
import com.aml_sakr.cryptotracker.core.data.networking.safeCall
import com.aml_sakr.cryptotracker.core.domain.util.NetworkError
import com.aml_sakr.cryptotracker.core.domain.util.Result
import com.aml_sakr.cryptotracker.core.domain.util.map
import com.aml_sakr.cryptotracker.crypto.data.mappers.toCoin
import com.aml_sakr.cryptotracker.crypto.data.networking.dto.CoinResponseDto
import com.aml_sakr.cryptotracker.crypto.domain.Coin
import com.aml_sakr.cryptotracker.crypto.domain.CoinDataSource
import io.ktor.client.HttpClient
import io.ktor.client.request.get


class RemoteCoinDataSource(
    private val httpClient: HttpClient
) : CoinDataSource {
    override suspend fun getCoins(): Result<List<Coin>, NetworkError> {
        return safeCall<CoinResponseDto> {
            httpClient.get(
                constructUrl("/assets")
            )
        }.map { response ->
            response.data.map { it.toCoin() }
        }
    }
}