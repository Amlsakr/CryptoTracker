package com.aml_sakr.cryptotracker.crypto.presentation.coin_list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.sp
import com.aml_sakr.cryptotracker.R.dimen
import com.aml_sakr.cryptotracker.crypto.domain.Coin
import com.aml_sakr.cryptotracker.crypto.presentation.models.CoinUI
import com.aml_sakr.cryptotracker.crypto.presentation.models.toCoinUI
import com.aml_sakr.cryptotracker.ui.theme.CryptoTrackerTheme

@Composable
fun CoinListItem(
    modifier: Modifier = Modifier,
    coinUI: CoinUI,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .clickable(onClick = onClick)
            .padding(dimensionResource(dimen._16)),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(dimen._16))
    ) {
        val contentColor = if (isSystemInDarkTheme()) Color.White else Color.Black
        Icon(
            imageVector = ImageVector.vectorResource(id = coinUI.iconRes),
            contentDescription = coinUI.name,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(dimensionResource(id = dimen._85))
        )
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = coinUI.symbol,
                fontSize = 20.sp,
                color = contentColor,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = coinUI.name,
                fontSize = 14.sp,
                color = contentColor,
                fontWeight = FontWeight.Light,
            )
        }
        Column(horizontalAlignment = Alignment.End) {
            Text(
                text = "$ ${coinUI.priceUsd.formatted}",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = contentColor
            )
            Spacer(modifier = Modifier.height(dimensionResource(dimen._8)))
            PriceChange(change = coinUI.changePercent24Hr)
        }
    }

}

@PreviewLightDark
@Composable
fun CoinListItemPreview() {
    CryptoTrackerTheme {
        CoinListItem(
            coinUI = previewCoin,
            onClick = {},
            modifier = Modifier.background(
                color = MaterialTheme.colorScheme.background
            )
        )
    }
}

internal val previewCoin = Coin(
    id = "bitcoin",
    rank = 1,
    name = "Bitcoin",
    symbol = "BTC",
    marketCapUsd = 12354298.87,
    priceUsd = 1234.56,
    changePercent24Hr = 0.12

).toCoinUI()