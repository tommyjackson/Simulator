package com.tjackapps.simulator.desktop.simulation

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.tjackapps.simulator.desktop.simulation.components.SimulatorWindow
import com.tjackapps.simulator.desktop.ui.SimulatorTheme

fun main() {
    application {
        Window(
            onCloseRequest = ::exitApplication,
            state = rememberWindowState(width = Dp.Unspecified, height = Dp.Unspecified),
            title = "Simulator",
            resizable = true,
        ) {
            SimulatorTheme {
                SimulatorWindow()
            }
        }
    }
}
