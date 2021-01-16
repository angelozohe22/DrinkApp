package com.angelo.drinkapp.ui.fragments.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.angelo.drinkapp.R
import com.angelo.drinkapp.data.model.Drink
import com.angelo.drinkapp.utils.fromUrl
import kotlinx.android.synthetic.main.drink_row.view.*

class MainAdapter(
    private val drinksList: List<Drink>,
    private val itemClickListener: OnDrinkClickListener
) :
    RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    interface OnDrinkClickListener {
        fun onDrinkClicked(drink: Drink)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.drink_row, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(drinksList[position])
    }

    override fun getItemCount(): Int = drinksList.size

    inner class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(drink: Drink) {
            itemView.img_drink.fromUrl(drink.image)
            itemView.tv_name_drink.text = drink.name
            itemView.tv_description_drink.text = drink.description
            itemView.setOnClickListener {
                itemClickListener.onDrinkClicked(drink)
            }
        }
    }

}