package com.tjackapps.simulator.desktop.simulation.particles

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.IntSize
import com.tjackapps.simulator.desktop.simulation.Force
import com.tjackapps.simulator.desktop.simulation.simulator.Multipliers
import com.tjackapps.simulator.desktop.simulation.simulator.SimulationObject
import com.tjackapps.simulator.desktop.simulation.simulator.SimulationObject.Particle.Companion.next
import com.tjackapps.simulator.extensions.nestedForEachFirstIndexed

data class ParticlesState(
    val particles: List<SimulationObject.Particle> = emptyList(),
    val size: IntSize = IntSize.Zero,
    var multipliers: Multipliers = Multipliers(),
) {

    companion object {

        fun ParticlesState.populate(density: Density) : ParticlesState {

            val list = mutableListOf<SimulationObject.Particle>()

            repeat(10) {
                list.add(SimulationObject.Particle.create(density))
            }

            return copy(
                particles = list,
                size = size,
            )
        }

        /**
         * @param time: Time in milliseconds since last update
         */
        fun ParticlesState.next(time: Float, density: Density): ParticlesState {
            val mapOfForcesToApply = mutableMapOf<Int, MutableList<Offset>>()

            // nested map over all particles to calculate the forces to apply
            particles.nestedForEachFirstIndexed { indexOfFirst, indexOfSecond, first, second ->

                // calculate the force of gravity to apply
                val gravityOnFirst = Force.Gravity.calculate(
                    first.position,
                    first.mass,
                    second.position,
                    second.mass,
                    multipliers.gravity,
                )

                val gx = gravityOnFirst.x
                val gy = gravityOnFirst.y

                val firstList = mapOfForcesToApply[indexOfFirst]

                if (firstList == null) {
                    mapOfForcesToApply[indexOfFirst] = mutableListOf(Offset(gravityOnFirst.x, gravityOnFirst.y))
                } else {
                    firstList.add(Offset(gravityOnFirst.x, gravityOnFirst.y))
                    mapOfForcesToApply[indexOfFirst] = firstList
                }

                val gravityOnSecond = Force.Gravity.calculate(
                    second.position,
                    second.mass,
                    first.position,
                    first.mass,
                    multipliers.gravity,
                )

                val secondList = mapOfForcesToApply[indexOfSecond]

                if (secondList == null) {
                    mapOfForcesToApply[indexOfSecond] = mutableListOf(Offset(gravityOnSecond.x, gravityOnSecond.y))
                } else {
                    secondList.add(Offset(gravityOnSecond.x, gravityOnSecond.y))
                    mapOfForcesToApply[indexOfSecond] = secondList
                }
            }

            val summedForcesToApply = mutableMapOf<Int, Offset>()
            mapOfForcesToApply.forEach { entry ->
                var sumOfForces: Offset = Offset.Zero

                entry.value.forEach {
                    sumOfForces += it // TODO combine force vectors properly
                }

                summedForcesToApply[entry.key] = sumOfForces
            }

            return copy(
                particles = particles.mapIndexed { index, particle ->
                    particle.next(
                        durationMillis = time,
                        multipliers = multipliers,
                        netForcesToApply = summedForcesToApply[index] ?: kotlin.run {
                            println("DEBUG: Force not found at index $index in map ${summedForcesToApply.toString()}")
                            Offset.Zero
                        },
                        density = density
                    )
                }
            )
        }
    }
}
