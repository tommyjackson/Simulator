package com.tjackapps.simulator.desktop.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val PrimaryLight = Color(0xFF6200EE)
val PrimaryDark = Color(0xFFBB86FC)
val PrimaryVariant = Color(0xFF3700B3)

val Secondary = Color(0xFF03DAC6)
val SecondaryVariant = Color(0xFF018786)

val TextLight = Color(0xFF000000)
val TextDark = Color(0xFFFFFFFF)

val BackgroundLight = Color(0xFFFFFFFF)
val BackgroundDark = Color(0xFF121212)

val SurfaceLight = Color(0x80E6E6E6)
val SurfaceDark = Color(0xFF1A1A1A)

val SurfaceColor: Color
    @Composable
    get() {
        return if (isSystemInDarkTheme()) SurfaceDark else SurfaceLight
    }
