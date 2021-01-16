package com.angelo.drinkapp.domain

import com.angelo.drinkapp.core.Resource
import com.angelo.drinkapp.data.local.db.DrinkEntity
import com.angelo.drinkapp.data.local.source.LocalDataSourceImpl
import com.angelo.drinkapp.data.model.Drink
import com.angelo.drinkapp.data.remote.source.RemoteDataSourceImpl

class DrinkRepositoryImpl(
    private val localDataSource: LocalDataSourceImpl,
    private val remoteDataSource: RemoteDataSourceImpl
) : DrinkRepository {

    override suspend fun getDrinkList(drinkName: String): Resource<List<Drink>> {
        return remoteDataSource.getDrinkList(drinkName)
    }

    override suspend fun getFavouriteDrinks(): Resource<List<DrinkEntity>> {
        return localDataSource.getAllFavouriteDrinks()
    }

    override suspend fun saveFavouriteDrink(drink: DrinkEntity) {
        localDataSource.insertDrinkIntoRoom(drink)
    }
}