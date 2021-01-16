package com.angelo.drinkapp.data.local.source

import com.angelo.drinkapp.core.Resource
import com.angelo.drinkapp.data.local.db.DrinkEntity

interface LocalDataSource {
    suspend fun getAllFavouriteDrinks(): Resource<List<DrinkEntity>>
    suspend fun insertDrinkIntoRoom(drink: DrinkEntity)
}