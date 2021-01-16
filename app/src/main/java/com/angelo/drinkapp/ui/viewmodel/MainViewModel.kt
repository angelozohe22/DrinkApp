package com.angelo.drinkapp.ui.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.angelo.drinkapp.core.Resource
import com.angelo.drinkapp.data.local.db.DrinkEntity
import com.angelo.drinkapp.domain.DrinkRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(private val drinkRepository: DrinkRepository): ViewModel() {
    
    private val _drinkName = MutableLiveData<String>()
    fun setDrinkName(drinkName: String){
        _drinkName.value = drinkName
    }

    init {
        setDrinkName("margarita")
    }

    val fetchDrinkList = _drinkName.distinctUntilChanged().switchMap {value->
        liveData(Dispatchers.IO) {
            emit(Resource.Loading())
            try {
                emit(drinkRepository.getDrinkList(value))
            }catch (e: Exception){
                emit(Resource.Failure(e))
                Log.e("aqui","Entre al catch de viewModel+${e.message}")
            }
        }
    }


    fun getFavouriteDrinks() = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            emit(drinkRepository.getFavouriteDrinks())
        }catch (e: Exception){
            emit(Resource.Failure(e))
            Log.e("aqui","Entre al catch de viewModel+${e.message}")
        }
    }

    fun saveFavouriteDrink(drink: DrinkEntity){
        viewModelScope.launch {
            withContext(Dispatchers.IO){ drinkRepository.saveFavouriteDrink(drink) }
        }
    }



}