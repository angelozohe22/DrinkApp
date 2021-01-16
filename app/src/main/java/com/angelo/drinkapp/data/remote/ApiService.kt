package com.angelo.drinkapp.data.remote

import com.angelo.drinkapp.data.model.Drink
import com.angelo.drinkapp.data.model.DrinkResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    //Pasamos en el get el parametro de busqueda -> EndPoint
    @GET("search.php") //?s= lo estamos pasando como parametro, entonces el value esta representado el ? =
    suspend fun getDrinkByName(@Query(value = "s") drinkName: String):DrinkResponse

}