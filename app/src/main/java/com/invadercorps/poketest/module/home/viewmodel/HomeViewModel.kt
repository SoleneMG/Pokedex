package com.invadercorps.poketest.module.home.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.invadercorps.poketest.data.repository.model.Pokemon

/**
 * This view model is used to transfer data between HomeActivity's fragments.
 * - PokemonListFragment update selected pokemon id
 * - PokemonListFragment listen selected pokemon id to update color of selected item in list
 * - PokemonDetailsFragment listen selected pokemon id to get corresponding details
 */
class HomeViewModel(application: Application) : AndroidViewModel(application) {
    private val _selectedPokemonId: MutableLiveData<String> =
        MutableLiveData<String>()
    val selectedPokemonId: LiveData<String> = _selectedPokemonId

    fun selectPokemon(pokemonId: String) {
        _selectedPokemonId.value = pokemonId
    }

    fun getPokemonId(): String? {
        return selectedPokemonId.value
    }
}