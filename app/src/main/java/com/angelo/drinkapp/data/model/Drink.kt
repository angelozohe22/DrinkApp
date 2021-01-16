package com.angelo.drinkapp.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Drink(
    @SerializedName("idDrink")
    val idDrink     : Int = 0,
    @SerializedName("strDrinkThumb")
    val image       : String = "",
    @SerializedName("strDrink")
    val name        : String = "",
    @SerializedName("strInstructions")
    val description : String = ""
):Parcelable

