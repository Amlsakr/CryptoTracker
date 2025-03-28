package com.aml_sakr.cryptotracker.crypto.presentation.coin_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aml_sakr.cryptotracker.core.domain.util.onError
import com.aml_sakr.cryptotracker.core.domain.util.onSuccess
import com.aml_sakr.cryptotracker.crypto.domain.CoinDataSource
import com.aml_sakr.cryptotracker.crypto.presentation.coin_detail.DataPoint
import com.aml_sakr.cryptotracker.crypto.presentation.models.CoinUI
import com.aml_sakr.cryptotracker.crypto.presentation.models.toCoinUI
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

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
    private val _events = Channel<CoinListEvent>()
    val events = _events.receiveAsFlow()

    private fun selectCoin(coinUI: CoinUI) {
        _state.update {
            it.copy(selectedCoin = coinUI)
        }
        viewModelScope.launch {
            coinDataSource.getCoinHistory(
                coinUI.id,
                start = ZonedDateTime.now().minusDays(5),
                end = ZonedDateTime.now()
            )
                .onSuccess { history ->
                    val dataPoints = history
                        .sortedBy { it.dateTime }
                        .map {
                            DataPoint(
                                x = it.dateTime.hour.toFloat(),
                                y = it.priceUsd.toFloat(),
                                xLabel = DateTimeFormatter.ofPattern("ha\nM/d").format(it.dateTime)
                            )
                        }
                    _state.update {
                        it.copy(
                            selectedCoin = it.selectedCoin?.copy(
                                coinPriceHistory = dataPoints
                            )
                        )
                    }
                }.onError {
                    _events.send(CoinListEvent.Error(it))
                }
        }

    }

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
                    _events.send(CoinListEvent.Error(error))
                }
        }
    }

    fun onAction(action: CoinListAction) {
        when (action) {
            is CoinListAction.CoinRefresh -> {
                loadCoins()
            }

            is CoinListAction.OnCoinClick -> {
                selectCoin(action.coinUI)

            }
        }
    }
}