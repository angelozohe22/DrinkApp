package com.angelo.drinkapp.data.model

import com.google.gson.annotations.SerializedName

data class DrinkResponse(
    @SerializedName("drinks")
    val drinkList: List<Drink>
)