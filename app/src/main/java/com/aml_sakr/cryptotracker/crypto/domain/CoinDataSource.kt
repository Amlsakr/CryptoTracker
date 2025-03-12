package com.aml_sakr.cryptotracker.crypto.domain

import com.aml_sakr.cryptotracker.core.domain.util.NetworkError
import com.aml_sakr.cryptotracker.core.domain.util.Result

interface CoinDataSource {
    suspend fun getCoins(): Result<List<Coin>, NetworkError>
}