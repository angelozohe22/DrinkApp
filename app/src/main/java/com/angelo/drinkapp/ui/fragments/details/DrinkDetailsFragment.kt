package com.angelo.drinkapp.ui.fragments.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.angelo.drinkapp.R
import com.angelo.drinkapp.data.local.db.DrinkDataBase
import com.angelo.drinkapp.data.local.db.DrinkEntity
import com.angelo.drinkapp.data.local.source.LocalDataSourceImpl
import com.angelo.drinkapp.data.model.Drink
import com.angelo.drinkapp.data.remote.source.RemoteDataSourceImpl
import com.angelo.drinkapp.domain.DrinkRepositoryImpl
import com.angelo.drinkapp.ui.viewmodel.MainViewModel
import com.angelo.drinkapp.ui.viewmodel.MainViewModelFactory
import com.angelo.drinkapp.utils.fromUrl
import kotlinx.android.synthetic.main.fragment_drink_details.*

class DrinkDetailsFragment : Fragment() {

    private lateinit var drink: Drink

    private val viewModel by activityViewModels<MainViewModel>
    {
        MainViewModelFactory(
            DrinkRepositoryImpl(
                LocalDataSourceImpl(DrinkDataBase.getInstance(requireContext().applicationContext)),
                RemoteDataSourceImpl()
            )
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireArguments().let {
            drink = it.getParcelable<Drink>("drink") as Drink
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_drink_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        img_drink_details.fromUrl(drink.image)
        tv_name_drink_details.text          = drink.name
        tv_description_drink_details.text   = drink.description

        btn_add_favorite.setOnClickListener {
            val idDrink       = drink.idDrink
            val image       = drink.image
            val name        = drink.name
            val description = drink.description

            viewModel.saveFavouriteDrink(DrinkEntity(idDrink,image,name,description))
            Toast.makeText(requireContext(), "Trago agregado a favoritos", Toast.LENGTH_SHORT).show()
        }
    }
}