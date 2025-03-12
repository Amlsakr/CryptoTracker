package com.aml_sakr.cryptotracker.crypto.presentation.coin_list

import androidx.compose.runtime.Immutable
import com.aml_sakr.cryptotracker.crypto.presentation.models.CoinUI

@Immutable
data class CoinListState(
    val isLoading: Boolean = false,
    val coinList: List<CoinUI> = emptyList(),
    val selectedCoin: CoinUI? = null
)
