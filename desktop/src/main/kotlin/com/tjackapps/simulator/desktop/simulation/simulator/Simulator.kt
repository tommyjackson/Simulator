package com.tjackapps.simulator.desktop.simulation.simulator

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.tjackapps.simulator.desktop.simulation.particles.ParticlesModel
import com.tjackapps.simulator.desktop.simulation.particles.ParticlesState

class Simulator {

    // Contains the necessary top level data to run the simulator
    var simulatorModel by mutableStateOf(
        SimulatorModel(
            SimulatorState()
        )
    )

    // Contains all data related to particles in the simulator
    var particlesModel by mutableStateOf(
        ParticlesModel(
            ParticlesState()
        )
    )

    // list of objects to be simulated (i.e. particles, user)
    val simulationObjects: List<SimulationObject>
        get() {
            return particlesModel.particlesState.particles
        }

    val status get(): SimulatorStatus = simulatorModel.simulatorState.simulatorStatus

    fun configureSimulation(
        config: SimulatorConfig,
    ) {
        simulatorModel.updateSimulationConfig(
            config = config,
        )

        particlesModel.updateSimulationConfig(
            config = config,
        )
    }

    private fun populateData() {
//        simulatorModel.updateSimulationSize(
//            sizePixels = sizePixels,
//        )

        particlesModel.populate()
    }

    fun startSimulation() {
        particlesModel.reset()
        populateData()
        simulatorModel.startSimulation()
        println("Simulation Started")
    }

    fun pauseSimulation() {
        simulatorModel.pauseSimulation()
        println("pauSimulation Paused")
    }

    fun resumeSimulation() {
        simulatorModel.resumeSimulation()
        println("Simulation Resumed")
    }

    fun stopSimulation() {
        simulatorModel.endSimulation()
        println("Simulation Stopped")
    }

    fun updateSimulation(
        timeElapsedMillis: Float,
    ) {
        if (status != SimulatorStatus.RUNNING) return

        particlesModel.next(timeElapsedMillis)
    }

    companion object {
        const val DEFAULT_PIXELS_PER_MILLIMETER = 16f
    }
}