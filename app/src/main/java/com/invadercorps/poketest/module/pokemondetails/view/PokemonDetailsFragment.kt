package com.invadercorps.poketest.module.pokemondetails.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.invadercorps.poketest.R
import com.invadercorps.poketest.data.repository.model.StatefulData
import com.invadercorps.poketest.data.repository.model.pokemondetails.PokeDetails
import com.invadercorps.poketest.databinding.PokemonDetailsFragmentBinding
import com.invadercorps.poketest.module.common.view.DrawableFactory
import com.invadercorps.poketest.module.home.viewmodel.HomeViewModel
import com.invadercorps.poketest.module.pokemondetails.view.PokemonDetailsActivity.Companion.EXTRA_SELECTED_POKEMON_ID
import com.invadercorps.poketest.module.pokemondetails.viewmodel.PokemonDetailsViewModel

class PokemonDetailsFragment : Fragment() {
    private lateinit var viewBinding: PokemonDetailsFragmentBinding
    private val pokemonDetailsViewModel: PokemonDetailsViewModel by viewModels()
    private val homeViewModel: HomeViewModel by activityViewModels()
    private val VIEW_EMPTY = 0
    private val VIEW_LOADING = 1
    private val VIEW_SUCCESS = 2
    private val VIEW_ERROR = 3

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewBinding = PokemonDetailsFragmentBinding.inflate(inflater, container, false)

        setupView()
        setupObservers()
        getPokemonDetailsOnPortraitOrientation()

        return viewBinding.root
    }

    /**
     * On portrait orientation this fragment is shown by PokemonDetailsActivity,
     * so HomeViewModel is no more accessible by HomeActivity scope to transfer
     * selectedPokemonId.
     * That's why we check intent containing id for this case.
     */
    private fun getPokemonDetailsOnPortraitOrientation() {
        activity?.intent?.getStringExtra(EXTRA_SELECTED_POKEMON_ID)?.let {
            pokemonDetailsViewModel.getPokemonDetails(it)
        }
    }

    private fun setupObservers(){
        pokemonDetailsViewModel.pokemonDetails.observe(viewLifecycleOwner, Observer {
            when (it) {
                is StatefulData.Empty -> viewBinding.root.displayedChild = VIEW_EMPTY
                is StatefulData.Loading -> viewBinding.root.displayedChild = VIEW_LOADING
                is StatefulData.Success -> {
                    viewBinding.root.displayedChild = VIEW_SUCCESS
                    displayPokemonDetails(it.result)
                }
                is StatefulData.Error -> viewBinding.root.displayedChild = VIEW_ERROR
            }
        })

        homeViewModel.selectedPokemonId.observe(viewLifecycleOwner, Observer {
                pokemonDetailsViewModel.getPokemonDetails(it)
        })
    }

    private fun setupView(){
        Glide
            .with(this)
            .load(R.drawable.poke_loader)
            .into(viewBinding.loader)
            viewBinding.retryBtn.setOnClickListener {
                /*
                 Landscape mode use pokemonId from home view model, beacause PokemonListFragment update value
                 Portrait mode cannot use home view model due to different activity, so should use value from intent
                 */
                val pokemonId = homeViewModel.getPokemonId() ?: activity?.intent?.getStringExtra(EXTRA_SELECTED_POKEMON_ID)
                pokemonId?.let { pokemonDetailsViewModel.getPokemonDetails(it) }
            }
    }

    private fun displayPokemonDetails(pokeDetails: PokeDetails) {
        viewBinding.abilities.text = pokeDetails.abilities.map { it.type.name }.joinToString(", ")
        viewBinding.types.text = pokeDetails.types.map { it.type.name }.joinToString (", ")
        viewBinding.name.text = pokeDetails.name
        Glide
            .with(viewBinding.sprite)
            .load(pokeDetails.sprite.frontDrawing)
            .placeholder(DrawableFactory.makeCircularProgressDrawable(requireContext()))
            .into(viewBinding.sprite)

    }
}