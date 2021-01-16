package com.angelo.drinkapp.ui.fragments.favourites

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.angelo.drinkapp.R
import com.angelo.drinkapp.core.Resource
import com.angelo.drinkapp.data.local.db.DrinkDataBase
import com.angelo.drinkapp.data.local.source.LocalDataSourceImpl
import com.angelo.drinkapp.data.model.Drink
import com.angelo.drinkapp.data.remote.source.RemoteDataSourceImpl
import com.angelo.drinkapp.domain.DrinkRepositoryImpl
import com.angelo.drinkapp.ui.fragments.main.MainAdapter
import com.angelo.drinkapp.ui.viewmodel.MainViewModel
import com.angelo.drinkapp.ui.viewmodel.MainViewModelFactory
import kotlinx.android.synthetic.main.fragment_favourite.*

class FavouriteFragment : Fragment(), MainAdapter.OnDrinkClickListener {

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
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favourite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
        setUpObservers()
    }

    private fun setUpObservers(){
        viewModel.getFavouriteDrinks().observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Resource.Loading -> {
                }
                is Resource.Success -> {
                    if (result.data.isEmpty())
                        Toast.makeText(requireContext(), "No hay tragos favoritos", Toast.LENGTH_SHORT).show()
                    else {
                        val favouriteDrinkList = result.data.map {
                            Drink(it.drinkId,it.image,it.name,it.description)
                        }
                        rv_favourite_drinks.adapter = MainAdapter(favouriteDrinkList,this)
                    }
                }
                is Resource.Failure -> {
                }
            }
        })
    }

    private fun setUpRecyclerView(){
        rv_favourite_drinks.apply {
            layoutManager = LinearLayoutManager(requireContext())
            addItemDecoration(
                DividerItemDecoration(requireContext(),
                    DividerItemDecoration.VERTICAL)
            )
        }
    }

    override fun onDrinkClicked(drink: Drink) {
        Toast.makeText(requireContext(), drink.name, Toast.LENGTH_SHORT).show()
    }

}