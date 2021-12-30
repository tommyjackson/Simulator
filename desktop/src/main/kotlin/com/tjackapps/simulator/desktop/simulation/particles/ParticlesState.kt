package com.tjackapps.simulator.desktop.simulation.particles

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.IntSize
import com.tjackapps.simulator.desktop.simulation.simulator.Multipliers
import com.tjackapps.simulator.desktop.simulation.simulator.SimulationObject
import com.tjackapps.simulator.desktop.simulation.simulator.SimulationObject.Particle.Companion.next

data class ParticlesState(
    val particles: List<SimulationObject.Particle> = emptyList(),
//    val particleRadius: Float = 1f,
    val size: IntSize = IntSize.Zero,
//    val speedMultiplier: Float = 1f,
//    val gravityMultiplier: Float = 1f,
    var multipliers: Multipliers = Multipliers(),
) {

    companion object {

        fun ParticlesState.populate() : ParticlesState {

            val list = mutableListOf<SimulationObject.Particle>()

            repeat(10) {
                list.add(SimulationObject.Particle.create())
            }

            return copy(
                particles = list,
                size = size,
            )
        }

        /**
         * @param time: Time in milliseconds since last update
         */
        fun ParticlesState.next(time: Float): ParticlesState {
//            val mapOfForcesToApply = mutableMapOf<Int, MutableList<Offset>>()

//            // nested map over all particles to calculate the forces to apply
//            particles.nestedForEachFirstIndexed { indexOfFirst, indexOfSecond, first, second ->
//
//                // calculate the force of gravity to apply
//                val gravity = Force.Gravity.calculate(
//                    first.position,
//                    first.mass,
//                    second.position,
//                    second.mass,
//                    gravityMultiplier,
//                )
//
//                val gx = gravity.x
//                val gy = gravity.y
//
//                val firstList = mapOfForcesToApply[indexOfFirst]
//
//                if (firstList == null) {
//                    mapOfForcesToApply[indexOfFirst] = mutableListOf(Offset(gravity.x, gravity.y))
//                } else {
//                    firstList.add(Offset(gravity.x, gravity.y))
//                    mapOfForcesToApply[indexOfFirst] = firstList
//                }
//
//                val secondList = mapOfForcesToApply[indexOfSecond]
//
//                if (secondList == null) {
//                    mapOfForcesToApply[indexOfSecond] = mutableListOf(Offset(gravity.x, gravity.y))
//                } else {
//                    secondList.add(Offset(gravity.x, gravity.y))
//                    mapOfForcesToApply[indexOfSecond] = secondList
//                }
//            }
//
//            val summedForcesToApply = mutableMapOf<Int, Offset>()
//            mapOfForcesToApply.forEach { entry ->
//                var sumOfForces: Offset = Offset.Zero
//
//                entry.value.forEach {
//                    sumOfForces += it // TODO combine force vectors properly
//                }
//
//                summedForcesToApply[entry.key] = sumOfForces
//            }

            return copy(
                particles = particles.mapIndexed { index, particle ->
                    particle.next(
                        borders = size,
                        durationMillis = time,
//                        radius = particleRadius,
                        netForcesToApply = Offset.Zero,
                        multipliers = multipliers,
//                        netForcesToApply = summedForcesToApply[index] ?: kotlin.run {
//                            println("DEBUG: Force not found at index $index in map ${summedForcesToApply.toString()}")
//                            Offset.Zero
//                        },
                    )
                }
            )
        }
    }
}