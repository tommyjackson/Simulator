package com.tjackapps.simulator.desktop.simulation.simulator

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp

sealed class SimulationObject {
    // how the object should be drawn on the canvas
    abstract fun draw(scope: DrawScope, scaleMultiplier: Float, color: Color)

    /**
     * width is always 1 millimeter
     * height is always 1 millimeter
     */
    data class Particle(
        val width: Float, // pixels (16px at 1x scale multiplier by default?)
        val height: Float, // pixels
        // TODO particles shouldn't have mass, but magic energy should have some conversion for mass on impact with objects
        // for now, mass will be included to help figure out formulas and get gravity working
        val mass: Float, // grams
        val position: Offset, // position on grid in meters (not pixels)
        val velocity: Offset, // meters/second
        val acceleration: Offset, // meters/second^2
    ): SimulationObject() {

        override fun draw(
            scope: DrawScope,
            scaleMultiplier: Float,
            color: Color,
        ) {
            with(scope) {
                drawCircle(
                    color = color,
                    radius = (width / 2) * scaleMultiplier, // TODO???
                    center = position,
                )
            }
        }

        companion object {
            fun create(density: Density): Particle {
                with(density) {
                    val w = 1200.dp.roundToPx()
                    val h = 800.dp.roundToPx()

                    val position = Offset(
                        (0..w).random().toFloat(),
                        (0..h).random().toFloat()
                    )

                    val velocity = Offset(
                        // First, randomize direction. Second, randomize magnitude of velocity.
                        listOf(
                            -1f,
                            1f
                        ).random() * ((w.toFloat() / 100f).toInt()..(w.toFloat() / 10f).toInt()).random()
                            .toFloat(),
                        listOf(
                            -1f,
                            1f
                        ).random() * ((h.toFloat() / 100f).toInt()..(h.toFloat() / 10f).toInt()).random()
                            .toFloat()
                    )

                    return Particle(
                        width = 8f,
                        height = 8f,
                        mass = (1..10).random().toFloat(),
                        position = position,
                        velocity = velocity,
                        acceleration = Offset.Zero,
                    )
                }
            }

            /**
             * Calculate this [MagicParticle]'s distance to another one.
             */
            infix fun Particle.distanceTo(another: Particle): Float {
                return (position - another.position).getDistance()
            }

            fun Particle.next(
                borderss: IntSize,
                durationMillis: Float,
//                radius: Float,
                netForcesToApply: Offset, // acceleration?
                multipliers: Multipliers,
                density: Density,
            ): Particle {
                val borders = with(density) {
                    IntSize(1200.dp.roundToPx(), 800.dp.roundToPx())
                }

                val speed = (velocity + netForcesToApply) * multipliers.speed
                val radius = 8 * multipliers.scale

                return Particle(
                    position = position + Offset(
                        x = speed.x / 1000f * durationMillis,
                        y = speed.y / 1000f * durationMillis,
                    ),
                    velocity = velocity,
                    acceleration = acceleration,
                    mass = mass,
                    width = width,
                    height = height,
                ).let {
                    val positionNest = it.position
                    val velocityNest = it.velocity
                    val borderTop = radius
                    val borderLeft = radius
                    val borderBottom = borders.height - radius
                    val borderRight = borders.width - radius
                    Particle(
                        position = Offset(
                            x = when {
                                positionNest.x < borderLeft -> {
                                    borderLeft - (positionNest.x - borderLeft)
                                }
                                positionNest.x > borderRight -> {
                                    borderRight - (positionNest.x - borderRight)
                                }
                                else -> {
                                    positionNest.x
                                }
                            },
                            y = when {
                                positionNest.y < borderTop -> {
                                    borderTop - (positionNest.y - borderTop)
                                }
                                positionNest.y > borderBottom -> {
                                    borderBottom - (positionNest.y - borderBottom)
                                }
                                else -> {
                                    positionNest.y
                                }
                            }
                        ),
                        velocity = Offset(
                            x = when {
                                positionNest.x < borderLeft -> {
                                    -velocityNest.x
                                }
                                positionNest.x > borderRight -> {
                                    -velocityNest.x
                                }
                                else -> {
                                    velocityNest.x
                                }
                            },
                            y = when {
                                positionNest.y < borderTop -> {
                                    -velocityNest.y
                                }
                                positionNest.y > borderBottom -> {
                                    -velocityNest.y
                                }
                                else -> {
                                    velocityNest.y
                                }
                            }
                        ),
                        acceleration = Offset.Zero, // TODO should we reset this to zero here? or return forces?
                        mass = mass,
                        width = width,
                        height = height,
                    )
                }
            }
        }
    }
}