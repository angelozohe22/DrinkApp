package com.angelo.drinkapp.data.remote.source

import com.angelo.drinkapp.core.Resource
import com.angelo.drinkapp.data.model.Drink

interface RemoteDataSource {

    suspend fun getDrinkList(drinkName: String): Resource<List<Drink>>

}