package com.aml_sakr.cryptotracker.crypto.presentation.coin_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.aml_sakr.cryptotracker.crypto.presentation.coin_list.components.CoinListItem
import com.aml_sakr.cryptotracker.crypto.presentation.coin_list.components.previewCoin
import com.aml_sakr.cryptotracker.crypto.presentation.models.CoinUI
import com.aml_sakr.cryptotracker.ui.theme.CryptoTrackerTheme

@Composable
fun CoinListScreen(modifier: Modifier = Modifier, coinListState: CoinListState) {
    if (coinListState.isLoading) {
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(coinListState.coinList) { coinUI ->
                CoinListItem(
                    coinUI = coinUI,
                    onClick = { /*TODO*/ },
                    modifier = Modifier.fillParentMaxWidth()
                )
                HorizontalDivider()
            }

        }
    }

}

@PreviewLightDark
@Composable
private fun CoinListScreenPreview() {
    CryptoTrackerTheme {
        CoinListScreen(
            coinListState = CoinListState(
                coinList = (1..100).map { previewCoin.copy(id = it.toString()) } as ArrayList<CoinUI>
            ),
            modifier = Modifier.background(MaterialTheme.colorScheme.background))
    }

}