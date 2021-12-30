package com.tjackapps.simulator.desktop.simulation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.tjackapps.simulator.desktop.simulation.simulator.Simulator
import com.tjackapps.simulator.desktop.simulation.simulator.SimulatorConfig
import com.tjackapps.simulator.desktop.simulation.simulator.SimulatorStatus
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SimulatorWindow() {
    val simulator = remember { Simulator() }
    var config by rememberSaveable { mutableStateOf(SimulatorConfig()) }
    val scaffoldState = rememberBottomSheetScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetPeekHeight = 0.dp,
        sheetBackgroundColor = MaterialTheme.colors.background.copy(alpha = 0.7f),
        sheetContentColor = MaterialTheme.colors.onBackground,
        sheetElevation = 0.dp,
        sheetGesturesEnabled = false,
        backgroundColor = MaterialTheme.colors.background,
        sheetContent = {
            Column(
                modifier = Modifier
                    .wrapContentSize()
                    .padding(top = 16.dp)
            ) {
                SliderRow(
                    title = "Size Scale Factor",
                    value = config.multipliers.scale,
                    valueRange = 0.1f..10f,
                    onValueChanged = {
                        config = config.update(
                            scale = it,
                        )
                        simulator.configureSimulation(config)
                    }
                )
                SliderRow(
                    title = "Speed Scale Factor",
                    value = config.multipliers.speed,
                    valueRange = 0.1f..10f,
                    onValueChanged = {
                        config = config.update(
                            speed = it,
                        )
                        simulator.configureSimulation(config)
                    }
                )
                SliderRow(
                    title = "Gravity Scale Factor",
                    value = config.multipliers.gravity,
                    valueRange = 0.1f..10f,
                    onValueChanged = {
                        config = config.update(
                            gravity = it,
                        )
                        simulator.configureSimulation(config)
                    }
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                ) {
                    Button(
                        onClick = { coroutineScope.launch { scaffoldState.bottomSheetState.collapse() } },
                        modifier = Modifier
                            .padding(8.dp)
                    ) {
                        Text("Done")
                    }
                }
            }
        },
    ) {
        with(config) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colors.background)
            ) {
                Box(
                    modifier = Modifier
                        .wrapContentSize()
                        .border(
                            width = 6.dp,
                            color = MaterialTheme.colors.secondary,
                            shape = RoundedCornerShape(6.dp),
                        )
                        .padding(6.dp)
                        .align(Alignment.Center)
                        .background(Color.Transparent),
                ) {
                    // actual simulator content
                    SimulatorComponent(
                        modifier = Modifier
                            .wrapContentSize()
                            .align(Alignment.Center)
                            .background(MaterialTheme.colors.surface),
                        simulator = simulator,
                        coroutineScope = coroutineScope,
                        scaleMultiplier = multipliers.scale,
                    )
                }

                if (scaffoldState.bottomSheetState.isCollapsed && !scaffoldState.bottomSheetState.isAnimationRunning) {
                    Row(
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .padding(8.dp)
                    ) {

                        val status = simulator.status

                        if (status != SimulatorStatus.STOPPED) {
                            Button(
                                onClick = {
                                    if (status == SimulatorStatus.RUNNING) {
                                        simulator.pauseSimulation()
                                    } else {
                                        simulator.resumeSimulation()
                                    }
                                },
                                modifier = Modifier
                                    .padding(8.dp),
                            ) {
                                if (status == SimulatorStatus.RUNNING) {
                                    Text("Pause")
                                } else {
                                    Text("Resume")
                                }
                            }
                        }
                        Button(
                            onClick = {
                                if (status == SimulatorStatus.STOPPED) {
                                    simulator.startSimulation()
                                } else {
                                    simulator.stopSimulation()
                                }
                            },
                            modifier = Modifier
                                .padding(8.dp),
                        ) {
                            if (status == SimulatorStatus.STOPPED) {
                                Text("Start")
                            } else {
                                Text("Stop")
                            }
                        }
                        Button(
                            onClick = {
                                coroutineScope.launch {
                                    scaffoldState.bottomSheetState.expand()
                                }
                            },
                            modifier = Modifier
                                .padding(8.dp),
                        ) {
                            Text("Options")
                        }
                    }
                }
            }
        }
    }
}