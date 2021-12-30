package com.tjackapps.simulator.theming

import androidx.compose.material.Typography
import androidx.compose.runtime.Composable

object Typographies {
    val roboto: Typography
        @Composable
        get() = Typography(defaultFontFamily = Fonts.roboto)
}
