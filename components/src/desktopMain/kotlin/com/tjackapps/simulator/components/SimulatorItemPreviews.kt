package com.tjackapps.simulator.components

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tjackapps.simulator.theming.SimulatorTheme

@Preview
@Composable
fun SimulatorItemPreview() {
  SimulatorTheme {
    SimulatorItem(
            name = "Name",
            modifier = Modifier
                    .padding(5.dp)
                    .width(100.dp)
    )
  }
}