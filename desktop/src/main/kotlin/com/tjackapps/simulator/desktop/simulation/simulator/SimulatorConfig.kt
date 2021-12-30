package com.tjackapps.simulator.desktop.simulation.simulator

data class SimulatorConfig(
    val multipliers: Multipliers = Multipliers(),
) {
    fun update(
        scale: Float = multipliers.scale,
        speed: Float = multipliers.speed,
        gravity: Float = multipliers.gravity,
    ): SimulatorConfig {
        return this.copy(
            multipliers = multipliers.copy(
                scale = scale,
                speed = speed,
                gravity = gravity,
            )
        )
    }
}
