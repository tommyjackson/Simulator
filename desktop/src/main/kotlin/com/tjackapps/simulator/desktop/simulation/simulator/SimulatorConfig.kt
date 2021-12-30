package com.tjackapps.simulator.desktop.simulation.simulator

data class SimulatorConfig(
    val multipliers: Multipliers = Multipliers(),
//    val threshold: Float = 0.06f, // TODO?
//    val maxThickness: Float = 6f, // TODO?
//    val dotRadius: Float = 8f, // pixels, 8 by default (1 millimeter)
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
