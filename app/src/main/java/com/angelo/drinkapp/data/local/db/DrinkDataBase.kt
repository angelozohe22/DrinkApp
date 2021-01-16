package com.angelo.drinkapp.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [DrinkEntity::class],
    version = 1
)
abstract class DrinkDataBase: RoomDatabase(){

    abstract fun drinkDao(): DrinkDao

    companion object{

        private var INSTANCE: DrinkDataBase? = null
        private const val DBNAME = "Drink.db"

        fun getInstance(ctx: Context): DrinkDataBase {
            val tempInstance = INSTANCE
            if(tempInstance != null) return tempInstance

            synchronized(this){
                val instance = Room.databaseBuilder(
                    ctx.applicationContext,
                    DrinkDataBase::class.java,
                    DBNAME
                ).build()
                INSTANCE = instance
                return instance
            }
        }

        fun destroyInstance(){
            INSTANCE = null
        }

    }

}