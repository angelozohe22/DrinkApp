package com.angelo.drinkapp.utils

import android.widget.ImageView
import com.squareup.picasso.Picasso

fun ImageView.fromUrl(url:String){
    Picasso.get().load(url).resize(120, 120)
        .centerCrop().into(this)
}