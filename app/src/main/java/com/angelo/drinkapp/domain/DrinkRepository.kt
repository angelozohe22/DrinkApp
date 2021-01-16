package com.angelo.drinkapp.domain

import com.angelo.drinkapp.core.Resource
import com.angelo.drinkapp.data.local.db.DrinkEntity
import com.angelo.drinkapp.data.model.Drink

interface DrinkRepository {

    suspend fun getDrinkList(drinkName: String): Resource<List<Drink>>
    suspend fun getFavouriteDrinks(): Resource<List<DrinkEntity>>
    suspend fun saveFavouriteDrink(drink: DrinkEntity)

}