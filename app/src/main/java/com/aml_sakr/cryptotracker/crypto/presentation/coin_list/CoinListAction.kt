package com.aml_sakr.cryptotracker.crypto.presentation.coin_list

import com.aml_sakr.cryptotracker.crypto.presentation.models.CoinUI

sealed interface CoinListAction {
    data class OnCoinClick(val coinUI: CoinUI) : CoinListAction
    data object CoinRefresh : CoinListAction
}