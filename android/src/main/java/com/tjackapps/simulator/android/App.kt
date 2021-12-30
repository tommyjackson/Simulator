package com.tjackapps.simulator.android

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.*
import com.tjackapps.simulator.theming.SimulatorTheme
import com.tjackapps.simulator.android.screens.SimulatorHomeViewModel

@Composable
fun App(
  homeScreenOpened: () -> Unit = {},
) {
  val navController = rememberNavController()
  LaunchedEffect(Unit) {
    navController.addOnDestinationChangedListener { _, dest, _ ->
      when (dest.route) {
        "simulator" -> homeScreenOpened()
        else -> TODO()
      }
    }
  }
  Surface(color = MaterialTheme.colors.background, modifier = Modifier.fillMaxSize()) {
    NavHost(navController, startDestination = "simulator") {
      composable("simulator") {
        SimulatorHomeViewModel()
      }
    }
  }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
  SimulatorTheme {
    App()
  }
}