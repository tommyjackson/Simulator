package com.tjackapps.simulator.desktop.simulation.particles

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.IntSize
import com.tjackapps.simulator.desktop.simulation.particles.ParticlesState.Companion.next
import com.tjackapps.simulator.desktop.simulation.particles.ParticlesState.Companion.populate
import com.tjackapps.simulator.desktop.simulation.simulator.SimulatorConfig

@Stable
class ParticlesModel(
    particlesState: ParticlesState
) {
    var particlesState by mutableStateOf(particlesState)

    fun updateSpeed(
        speed: Float,
    ) {
        particlesState = particlesState.copy(
//            speedMultiplier = speed,
        )
    }

    /**
     * @param time: Time in milliseconds since last update
     */
    fun next(time: Float, density: Density) {
        particlesState = particlesState.next(time, density)
    }

    fun updateSimulationConfig(
        config: SimulatorConfig,
    ) {
        particlesState = particlesState.copy(
            multipliers = config.multipliers,
        )
    }

    fun populate(density: Density) {
        particlesState = particlesState.populate(density)
    }

    fun reset() {
        particlesState = ParticlesState()
    }

    companion object {
        val Saver = Saver<ParticlesModel, ParticlesState>(
            save = { value -> value.particlesState },
            restore = { ParticlesModel(it) }
        )
    }
}