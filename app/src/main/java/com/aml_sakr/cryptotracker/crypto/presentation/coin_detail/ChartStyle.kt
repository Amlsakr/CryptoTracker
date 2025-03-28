package com.aml_sakr.cryptotracker.crypto.presentation.coin_detail

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit

data class ChartStyle(
    val chartLineColor : Color,
    val unSelectedColor : Color,
    val selectedColor : Color,
    val helperLineThicknessPX: Float,
    val axisLinesThicknessPX: Float,
    val labelFontSize : TextUnit,
    val minYLabelSpacing :Dp,
    val verticalPadding : Dp,
    val horizontalPadding : Dp,
    val xAxisLabelSpacing : Dp
)
