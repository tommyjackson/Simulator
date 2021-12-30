package com.tjackapps.simulator.android

import androidx.lifecycle.ViewModel
import com.tjackapps.simulator.data.DataRepository
import kotlinx.coroutines.flow.flow

class SimulatorViewModel : ViewModel() {
  private val repository = DataRepository()

  fun getPopulars() = flow {
    emit(repository.getData())
  }
}
