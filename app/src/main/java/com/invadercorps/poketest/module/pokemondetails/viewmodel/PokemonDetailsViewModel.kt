package com.invadercorps.poketest.module.pokemondetails.viewmodel

import android.app.Application
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.invadercorps.poketest.data.repository.PokeService
import com.invadercorps.poketest.data.repository.model.StatefulData
import com.invadercorps.poketest.data.repository.model.pokemondetails.PokeDetails
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PokemonDetailsViewModel(application: Application, val pokeService: PokeService) : AndroidViewModel(application) {

    private val _pokemonDetails: MutableLiveData<StatefulData<PokeDetails>> = MutableLiveData<StatefulData<PokeDetails>>(StatefulData.Empty())
    val pokemonDetails: LiveData<StatefulData<PokeDetails>> = _pokemonDetails

    fun getPokemonDetails(pokemonId: String?) {
        _pokemonDetails.value = StatefulData.Loading()
        val pokemonDetails = pokeService.getPokemonDetails(pokemonId)

        //we use handler to visualize loading
        Handler(Looper.getMainLooper()).postDelayed({
            pokemonDetails.enqueue(object : Callback<PokeDetails> {
                override fun onResponse(call: Call<PokeDetails>, response: Response<PokeDetails>) {
                    response.body()?.let { _pokemonDetails.value = StatefulData.Success(it) }
                }

                override fun onFailure(call: Call<PokeDetails>, t: Throwable) {
                    _pokemonDetails.value = StatefulData.Error()
                }
            })
        }, 2000)

    }



}

