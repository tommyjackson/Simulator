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
//    Box(
//        modifier = modifier
//            .simulatorComponent(
//                simulator = simulator,
//                coroutineScope = coroutineScope,
//                scaleMultiplier = scaleMultiplier,
//            )
//    )
//    val scrollState = rememberScrollState()

    SimulatorCanvas(
        modifier = modifier,
//            .horizontalScroll(scrollState)
//            .verticalScroll(scrollState),
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
    // setup simulation loop
    LaunchedEffect(Unit) {
        var lastTime = 0L
        while (isActive) {
            withFrameNanos {
                if (lastTime != 0L) {
                    val periodNanos = it - lastTime
                    val periodMillis = (periodNanos / 1E8).toFloat()
                    simulator.updateSimulation(periodMillis)
                }
                lastTime = it
            }
        }
    }

    val particleColor = MaterialTheme.colors.onSurface

    Canvas(
        modifier = modifier
//        modifier = Modifier
            .width(1200.dp)
            .height(800.dp),
    ) {
        simulator.simulationObjects.forEach {
            val px = (it as SimulationObject.Particle).position.x
            val py = (it as SimulationObject.Particle).position.y
            val vx = (it as SimulationObject.Particle).velocity.x
            val vy = (it as SimulationObject.Particle).velocity.y
            it.draw(this, scaleMultiplier, particleColor)
        }
    }
}

fun Modifier.simulatorComponent(
    simulator: Simulator,
    coroutineScope: CoroutineScope,
    scaleMultiplier: Float,
): Modifier {
    return composed {

        // setup simulation loop
        LaunchedEffect(Unit) {
            var lastTime = 0L
            while (isActive) {
                withFrameNanos {
                    if (lastTime != 0L) {
                        val periodNanos = it - lastTime
                        val periodMillis = (periodNanos / 1E8).toFloat()
                        simulator.updateSimulation(periodMillis)
                    }
                    lastTime = it
                }
            }
        }

//    pointerInput(Unit) {
//        // TODO
//    }
        val particleColor = MaterialTheme.colors.onSurface

        Modifier
//            .onSizeChanged {
//                simulator.onSizeChanged(it)
//            }
            .drawBehind {
                simulator.simulationObjects.forEach {
                    it.draw(this, scaleMultiplier, particleColor)
                }
            }
    }
}
