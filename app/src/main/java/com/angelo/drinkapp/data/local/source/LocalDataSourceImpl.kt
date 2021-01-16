package com.angelo.drinkapp.data.local.source

import com.angelo.drinkapp.core.Resource
import com.angelo.drinkapp.data.local.db.DrinkDataBase
import com.angelo.drinkapp.data.local.db.DrinkEntity
import com.angelo.drinkapp.data.local.source.LocalDataSource

class LocalDataSourceImpl(private val drinkDataBase: DrinkDataBase): LocalDataSource {

    override suspend fun getAllFavouriteDrinks(): Resource<List<DrinkEntity>> {
        return Resource.Success(drinkDataBase.drinkDao().getAllFavouriteDrinks())
    }

    override suspend fun insertDrinkIntoRoom(drink: DrinkEntity) {
        drinkDataBase.drinkDao().insertFavouriteDrink(drink)
    }
}