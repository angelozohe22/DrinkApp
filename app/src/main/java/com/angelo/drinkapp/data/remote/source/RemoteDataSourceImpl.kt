package com.angelo.drinkapp.data.remote.source

import com.angelo.drinkapp.core.Resource
import com.angelo.drinkapp.data.model.Drink
import com.angelo.drinkapp.data.remote.RetrofitClient

class RemoteDataSourceImpl: RemoteDataSource {

    override suspend fun getDrinkList(drinkName: String): Resource<List<Drink>> {
        val retrofitInstance = RetrofitClient.apiService
        return Resource.Success(retrofitInstance.getDrinkByName(drinkName).drinkList)
    }
}