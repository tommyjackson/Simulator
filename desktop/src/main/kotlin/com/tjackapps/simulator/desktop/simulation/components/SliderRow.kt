package com.tjackapps.simulator.desktop.simulation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tjackapps.simulator.desktop.ui.SimulatorTypography
import java.math.BigDecimal.ROUND_HALF_UP
import java.math.RoundingMode
import kotlin.math.round
import kotlin.math.roundToInt

@Composable
fun SliderRow(
    title: String,
    value: Float,
    valueRange: ClosedFloatingPointRange<Float>,
    onValueChanged: (Float) -> Unit,
) {
    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        Text(
            text = "$title: $value",
            style = SimulatorTypography.H2,
        )
        Slider(
            value = value,
            valueRange = valueRange,
            onValueChange = {
                val rounded = it.toBigDecimal().setScale(2, RoundingMode.HALF_UP).toFloat()
                onValueChanged(rounded)
            }
        )
    }
}