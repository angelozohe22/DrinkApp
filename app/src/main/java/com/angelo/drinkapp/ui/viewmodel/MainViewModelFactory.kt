package com.angelo.drinkapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.angelo.drinkapp.domain.DrinkRepository

class MainViewModelFactory (private val drinkRepository: DrinkRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(DrinkRepository::class.java).newInstance(drinkRepository)
    }
}