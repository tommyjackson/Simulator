package com.tjackapps.simulator.desktop.simulation

import androidx.compose.ui.geometry.Offset
import kotlin.math.pow

sealed class Force {

    companion object {
        private val GRAVITY_CONSTANT = (6.67 * (10.0).pow(-11))
    }

    object Gravity : Force() {

        fun calculate(
            objectOnePosition: Offset,
            objectOneMass: Float,
            objectTwoPosition: Offset,
            objectTwoMass: Float,
            multiplier: Float,
        ): Offset {
            val displacement = (objectTwoPosition - objectOnePosition)

            val normalizedDisplacement = displacement / displacement.getDistance()

            val forceOfGravityOnObjectOne =
                (GRAVITY_CONSTANT * 10f.pow(multiplier)) * ((objectOneMass * objectTwoMass) / (displacement.getDistance().pow(2)))
            return normalizedDisplacement * forceOfGravityOnObjectOne.toFloat()

            // TODO find way to define or customize how multiplier is applied here  (GRAVITY_CONSTANT * 10f.pow(multiplier))
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
