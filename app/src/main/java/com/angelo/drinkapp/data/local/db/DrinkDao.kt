package com.angelo.drinkapp.data.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DrinkDao {

    @Query("SELECT * FROM tb_drink")
    suspend fun getAllFavouriteDrinks():List<DrinkEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    //onConflict me permite reemplazar el dato en tabla si es que se agrego un drink por 2da vez
    suspend fun insertFavouriteDrink(drink: DrinkEntity)

}