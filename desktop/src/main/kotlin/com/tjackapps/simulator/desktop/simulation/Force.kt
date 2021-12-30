package com.tjackapps.simulator.desktop.simulation

import androidx.compose.ui.geometry.Offset
import kotlin.math.pow

sealed class Force {

    companion object {
        private val GRAVITY_CONSTANT = (-6.67 * (10.0).pow(1)) //actual -(6.674 * (10.0.pow(11.0)))
    }

    object Gravity : Force() {

        fun calculate(
            objectOnePosition: Offset,
            objectOneMass: Float,
            objectTwoPosition: Offset,
            objectTwoMass: Float,
            multiplier: Float,
        ): Offset {

            val distance = (objectTwoPosition - objectOnePosition)

            val forceOfGravityOnObjectOne =
                (GRAVITY_CONSTANT * multiplier) * ((objectOneMass * objectTwoMass) / ((distance).getDistance().pow(2)))
            return objectOnePosition.div(forceOfGravityOnObjectOne.toFloat())
        }
    }

    object Wind : Force() {

        fun calculate(
            windVelocity: Offset,
            objectOneVelocity: Offset,
            objectOneMass: Int,
        ): Float {
            return 10f // the force of the wind to apply on object one
        }
    }

}
