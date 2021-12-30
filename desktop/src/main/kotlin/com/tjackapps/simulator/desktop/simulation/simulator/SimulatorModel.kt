package com.tjackapps.simulator.desktop.simulation.simulator

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

@Stable
class SimulatorModel(
    simulatorState: SimulatorState,
) {
    var simulatorState by mutableStateOf(simulatorState)

    fun updateSimulationConfig(
        config: SimulatorConfig,
    ) {
        simulatorState = simulatorState.copy(
            multipliers = config.multipliers,
        )
    }

    fun startSimulation() {
        if (simulatorState.simulatorStatus == SimulatorStatus.STOPPED) {
            simulatorState = simulatorState.copy(
                simulatorStatus = SimulatorStatus.RUNNING
            )
        }
    }

    fun pauseSimulation() {
        if (simulatorState.simulatorStatus == SimulatorStatus.RUNNING) {
            simulatorState = simulatorState.copy(
                simulatorStatus = SimulatorStatus.PAUSED
            )
        }
    }

    fun resumeSimulation() {
        if (simulatorState.simulatorStatus == SimulatorStatus.PAUSED) {
            simulatorState = simulatorState.copy(
                simulatorStatus = SimulatorStatus.RUNNING
            )
        }
    }

    fun endSimulation() {
        if (simulatorState.simulatorStatus != SimulatorStatus.STOPPED) {
            simulatorState = SimulatorState()
        }
    }
}