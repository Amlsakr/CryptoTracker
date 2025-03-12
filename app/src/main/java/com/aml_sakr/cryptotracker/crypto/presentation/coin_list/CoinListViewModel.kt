package com.aml_sakr.cryptotracker.crypto.presentation.coin_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aml_sakr.cryptotracker.core.domain.util.onError
import com.aml_sakr.cryptotracker.core.domain.util.onSuccess
import com.aml_sakr.cryptotracker.crypto.domain.CoinDataSource
import com.aml_sakr.cryptotracker.crypto.presentation.models.toCoinUI
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CoinListViewModel(
    private val coinDataSource: CoinDataSource
) : ViewModel() {
    private val _state = MutableStateFlow(CoinListState())
    val state = _state.onStart {
        loadCoins()
    }.stateIn(
        viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = CoinListState()
    )

    private fun loadCoins() {
        viewModelScope.launch {
            _state.update {
                it.copy(isLoading = true)
            }
            coinDataSource.getCoins()
                .onSuccess { coins ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            coinList = coins.map { it.toCoinUI() })
                    }
                }
                .onError { error ->
                    _state.update { it.copy(isLoading = false) }
                }
        }
    }

    fun onAction(action: CoinListAction) {
        when (action) {
            is CoinListAction.CoinRefresh -> {
                loadCoins()
            }

            is CoinListAction.OnCoinClick -> {

            }
        }
    }
}