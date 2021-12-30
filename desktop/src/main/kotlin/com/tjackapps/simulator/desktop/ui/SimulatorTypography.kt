package com.tjackapps.simulator.desktop.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

object SimulatorTypography {

    val H1: TextStyle
        @Composable
        get() {
            return TextStyle(
                lineHeight = 40.sp,
                fontSize = 32.sp,
                fontWeight = FontWeight(400),
                fontFamily = appFontFamily,
                letterSpacing = (-0.18).sp,
                color = if (isSystemInDarkTheme()) TextDark else TextLight
            )
        }

    val H2: TextStyle
        @Composable
        get() {
            return TextStyle(
                fontSize = 24.sp,
                lineHeight = 32.sp,
                fontWeight = FontWeight(400),
                fontFamily = appFontFamily,
                color = if (isSystemInDarkTheme()) TextDark else TextLight
            )
        }

    val H3: TextStyle
        @Composable
        get() {
            return TextStyle(
                fontSize = 20.sp,
                lineHeight = 28.sp,
                fontWeight = FontWeight(400),
                fontFamily = appFontFamily,
                color = if (isSystemInDarkTheme()) TextDark else TextLight
            )
        }

    val SubHeader: TextStyle
        @Composable
        get() {
            return TextStyle(
                fontSize = 18.sp,
                lineHeight = 24.sp,
                fontWeight = FontWeight(400),
                fontFamily = appFontFamily,
                color = if (isSystemInDarkTheme()) TextDark else TextLight
            )
        }

    val Body: TextStyle
        @Composable
        get() {
            return TextStyle(
                fontSize = 16.sp,
                lineHeight = 22.sp,
                fontWeight = FontWeight(200),
                fontFamily = appFontFamily,
                color = if (isSystemInDarkTheme()) TextDark else TextLight
            )
        }

    val Caption: TextStyle
        @Composable
        get() {
            return TextStyle(
                fontSize = 14.sp,
                lineHeight = 20.sp,
                fontWeight = FontWeight(200),
                fontFamily = appFontFamily,
                color = if (isSystemInDarkTheme()) TextDark else TextLight
            )
        }

    val Overline: TextStyle
        @Composable
        get() {
            return TextStyle(
                fontSize = 12.sp,
                lineHeight = 18.sp,
                fontWeight = FontWeight(200),
                fontFamily = appFontFamily,
                color = if (isSystemInDarkTheme()) TextDark else TextLight
            )
        }

    private val defaultTypography = Typography()

    val Typography: Typography
        @Composable
        get() {
            return Typography(
                h1 = H1.copy(fontFamily = appFontFamily),
                h2 = H2.copy(fontFamily = appFontFamily),
                h3 = H3.copy(fontFamily = appFontFamily),
                h4 = defaultTypography.h4.copy(fontFamily = appFontFamily),
                h5 = defaultTypography.h5.copy(fontFamily = appFontFamily),
                h6 = defaultTypography.h6.copy(fontFamily = appFontFamily),
                subtitle1 = SubHeader.copy(fontFamily = appFontFamily),
                subtitle2 = defaultTypography.subtitle2.copy(fontFamily = appFontFamily),
                body1 = Body.copy(fontFamily = appFontFamily),
                body2 = defaultTypography.body2.copy(fontFamily = appFontFamily),
                button = defaultTypography.button.copy(
                    fontFamily = appFontFamily,
                ),
                caption = Caption.copy(fontFamily = appFontFamily),
                overline = defaultTypography.overline.copy(fontFamily = appFontFamily)
            )
        }
}