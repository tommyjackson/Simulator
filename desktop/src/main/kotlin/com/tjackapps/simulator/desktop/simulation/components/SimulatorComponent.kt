package com.tjackapps.simulator.desktop.simulation.components

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.withFrameNanos
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.tjackapps.simulator.desktop.simulation.simulator.SimulationObject
import com.tjackapps.simulator.desktop.simulation.simulator.Simulator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.isActive

@Composable
fun BoxScope.SimulatorComponent(
    modifier: Modifier,
    simulator: Simulator,
    coroutineScope: CoroutineScope,
    scaleMultiplier: Float,
) {

    SimulatorCanvas(
        modifier = modifier,
        simulator = simulator,
        coroutineScope = coroutineScope,
        scaleMultiplier = scaleMultiplier,
    )
}

@Composable
fun SimulatorCanvas(
    modifier: Modifier,
    simulator: Simulator,
    coroutineScope: CoroutineScope,
    scaleMultiplier: Float,
) {
    val density = LocalDensity.current

    // setup simulation loop
    LaunchedEffect(Unit) {
        var lastTime = 0L
        while (isActive) {
            withFrameNanos {
                if (lastTime != 0L) {
                    val periodNanos = it - lastTime
                    val periodMillis = (periodNanos / 1E6).toFloat()
                    simulator.updateSimulation(periodMillis, density)
                }
                lastTime = it
            }
        }
    }

    val particleColor = MaterialTheme.colors.onSurface

    Canvas(
        modifier = modifier
            .width(1200.dp)
            .height(800.dp),
    ) {
        simulator.simulationObjects.forEach {
            it.draw(this, scaleMultiplier, particleColor)
        }
    }
}
