package com.angelo.drinkapp.ui.fragments.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.angelo.drinkapp.R
import com.angelo.drinkapp.core.Resource
import com.angelo.drinkapp.data.local.db.DrinkDataBase
import com.angelo.drinkapp.data.local.source.LocalDataSourceImpl
import com.angelo.drinkapp.data.model.Drink
import com.angelo.drinkapp.data.remote.source.RemoteDataSourceImpl
import com.angelo.drinkapp.domain.DrinkRepositoryImpl
import com.angelo.drinkapp.ui.viewmodel.MainViewModel
import com.angelo.drinkapp.ui.viewmodel.MainViewModelFactory
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment(), MainAdapter.OnDrinkClickListener {

    private val viewModel by activityViewModels<MainViewModel>
    {
        MainViewModelFactory(
            DrinkRepositoryImpl(
                LocalDataSourceImpl(DrinkDataBase.getInstance(requireContext().applicationContext)),
                RemoteDataSourceImpl()
            )
        )
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRecyclerView()
        setUpObserver()
        setUpSearchView()

        btn_favourite.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_favouriteFragment)
        }

    }

    private fun setUpRecyclerView(){
        rv_drink.apply {
            layoutManager = LinearLayoutManager(requireContext())
            addItemDecoration(DividerItemDecoration(requireContext(),DividerItemDecoration.VERTICAL))
        }
    }

    private fun setUpObserver(){
        viewModel.fetchDrinkList.observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Resource.Loading -> {
                    progress_bar.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    progress_bar.visibility = View.GONE
                    rv_drink.adapter = MainAdapter(result.data,this)
                }
                is Resource.Failure -> {
                    progress_bar.visibility = View.GONE
                    Toast.makeText(requireContext(), "Hubo un error: ${result.exception}", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun setUpSearchView(){
        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.setDrinkName(query ?: "")
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    override fun onDrinkClicked(drink: Drink) {
        val localData = Bundle()
        localData.putParcelable("drink",drink)
        findNavController().navigate(R.id.action_mainFragment_to_drinkDetailsFragment,localData)
    }



}