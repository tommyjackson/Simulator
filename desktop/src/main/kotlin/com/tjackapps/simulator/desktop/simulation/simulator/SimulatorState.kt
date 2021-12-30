package com.tjackapps.simulator.desktop.simulation.simulator

import androidx.compose.ui.unit.IntSize

/**
 * Top level data for the [Simulator]
 *
 * @param lastUpdatedTimeInMillis: time the last update was processed
 * @param sizePixels: visible size of the window in pixels
 * @param simulatorStatus: current status of the simulator - either STOPPED, RUNNING, or PAUSED
 * @param multipliers: multipliers to apply to the simulation
 */
data class SimulatorState(
    var lastUpdatedTimeInMillis: Long = 0L,
    var sizePixels: IntSize = IntSize.Zero,
    var simulatorStatus: SimulatorStatus = SimulatorStatus.STOPPED,
    var multipliers: Multipliers = Multipliers(),
)