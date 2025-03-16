package com.aml_sakr.cryptotracker.crypto.presentation.coin_list

import com.aml_sakr.cryptotracker.core.domain.util.NetworkError

interface CoinListEvent {
    data class Error(val error: NetworkError) : CoinListEvent
}