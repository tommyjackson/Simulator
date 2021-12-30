package com.tjackapps.simulator.android.composables

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.tjackapps.simulator.theming.SimulatorTheme


@Composable
fun SimulatorHome() {
  // todo
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun SimulatorHomePreview() {
  SimulatorTheme(isDarkMode = true) {
    SimulatorHome()
  }
}
