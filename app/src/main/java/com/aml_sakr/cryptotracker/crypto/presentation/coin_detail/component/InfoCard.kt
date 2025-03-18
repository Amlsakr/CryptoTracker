package com.aml_sakr.cryptotracker.crypto.presentation.coin_detail.component

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.sp
import com.aml_sakr.cryptotracker.R
import com.aml_sakr.cryptotracker.ui.theme.CryptoTrackerTheme

@Composable
fun InfoCard(
    modifier: Modifier = Modifier,
    title: String,
    formattedText: String,
    icon: ImageVector,
    contentColor: Color = MaterialTheme.colorScheme.onSurface,
) {
    val defaultTextStyle = LocalTextStyle.current.copy(
        textAlign = TextAlign.Center,
        fontSize = 18.sp,
        color = contentColor
    )
    Card(
        modifier = modifier
            .padding(dimensionResource(R.dimen._8))
            .shadow(
                elevation = dimensionResource(R.dimen._15),
                shape = RectangleShape,
                ambientColor = MaterialTheme.colorScheme.primary,
                spotColor = MaterialTheme.colorScheme.primary
            ),
        shape = RectangleShape,
        border = BorderStroke(
            width = dimensionResource(R.dimen._1),
            color = MaterialTheme.colorScheme.primary
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer,
            contentColor = contentColor
        )
    ) {
        AnimatedContent(
            targetState = icon,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            label = stringResource(R.string.icon_animation)
        ) { icon ->
            Icon(
                imageVector = icon, contentDescription = title,
                modifier = Modifier
                    .size(
                        dimensionResource(R.dimen._75)
                    )
                    .padding(top = dimensionResource(R.dimen._16)),
                tint = contentColor
            )
        }
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen._8)))

        AnimatedContent(
            targetState = formattedText,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            label = stringResource(R.string.value_animation)
        ) { formattedText ->
            Text(
                text = formattedText,
                style = defaultTextStyle,
                modifier = Modifier.padding(horizontal = dimensionResource(R.dimen._16))
            )

        }

        Spacer(modifier = Modifier.height(dimensionResource(R.dimen._8)))
        Text(
            text = title,
            style = defaultTextStyle,
            modifier = Modifier
                .padding(horizontal = dimensionResource(R.dimen._16))
                .padding(bottom = dimensionResource(R.dimen._16)),
            fontSize = 12.sp,
            fontWeight = FontWeight.Light,
            color = contentColor
        )
    }
}

@PreviewLightDark
@Composable
private fun InfoCardPreview() {
    CryptoTrackerTheme {
        InfoCard(
            title = "Price",
            formattedText = "$ 63,157.44",
            icon = ImageVector.vectorResource(R.drawable.dollar)
        )
    }
}