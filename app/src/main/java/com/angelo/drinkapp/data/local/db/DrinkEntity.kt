package com.angelo.drinkapp.data.local.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tb_drink")
data class DrinkEntity(
    @PrimaryKey
    val drinkId     : Int,
    @ColumnInfo(name = "drink_image")
    val image       : String,
    @ColumnInfo(name = "drink_name")
    val name        : String,
    @ColumnInfo(name = "drink_description")
    val description : String
)