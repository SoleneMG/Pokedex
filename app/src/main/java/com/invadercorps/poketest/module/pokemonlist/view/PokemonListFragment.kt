package com.invadercorps.poketest.module.pokemonlist.view

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.invadercorps.poketest.R
import com.invadercorps.poketest.data.repository.model.StatefulData
import com.invadercorps.poketest.databinding.PokemonListFragmentBinding
import com.invadercorps.poketest.module.home.viewmodel.HomeViewModel
import com.invadercorps.poketest.module.pokemondetails.view.PokemonDetailsActivity
import com.invadercorps.poketest.module.pokemonlist.viewmodel.PokemonListViewModel

class PokemonListFragment : Fragment(), PokemonListAdapter.OnItemClickListener {

    private lateinit var viewBinding: PokemonListFragmentBinding
    private lateinit var listAdapter: PokemonListAdapter
    private val pokemonListViewModel: PokemonListViewModel by viewModels()
    private val homeViewModel: HomeViewModel by activityViewModels()
    private val VIEW_LOADING = 0
    private val VIEW_SUCCESS = 1
    private val VIEW_ERROR = 2

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        viewBinding = PokemonListFragmentBinding.inflate(inflater, container, false)
        setupView()
        setupObservers()

         return viewBinding.root
    }

    private fun setupObservers() {
        pokemonListViewModel.pokemonList.observe(viewLifecycleOwner, Observer {
            when (it) {
                is StatefulData.Success -> {
                    listAdapter.setItems(it.result.results)
                    viewBinding.root.displayedChild = VIEW_SUCCESS
                }
                is StatefulData.Error -> viewBinding.root.displayedChild = VIEW_ERROR
                is StatefulData.Empty -> pokemonListViewModel.getPokemonList()
                is StatefulData.Loading -> viewBinding.root.displayedChild = VIEW_LOADING
            }
        })

        homeViewModel.selectedPokemonId.observe(viewLifecycleOwner, Observer {
            listAdapter.setSelectedPokemonId(it)
        })
    }

    private fun setupView() {
        Glide
            .with(this)
            .load(R.drawable.poke_loader)
            .into(viewBinding.loader)
        listAdapter = PokemonListAdapter(resources.configuration.orientation)
        listAdapter.setContext(requireContext())
        listAdapter.setOnItemClickListener(this)
        viewBinding.pokemonListRecycler.adapter = listAdapter
        viewBinding.pokemonListRecycler.layoutManager = LinearLayoutManager(context)
        viewBinding.retryBtn.setOnClickListener { pokemonListViewModel.getPokemonList() }
    }

    override fun onItemClick(pokemonId: String) {
        homeViewModel.selectPokemon(pokemonId)

        /*
        In portrait mode we open PokemonDetailsFragment in other activity,
        so we must send selected pokemon id.
         */
        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            val intent = Intent(activity, PokemonDetailsActivity::class.java).apply {
                putExtra(PokemonDetailsActivity.EXTRA_SELECTED_POKEMON_ID, pokemonId)
            }
            startActivity(intent)
        }
    }
}
