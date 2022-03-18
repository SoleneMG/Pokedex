package com.invadercorps.poketest.module.pokemonlist.viewmodel

import android.app.Application
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.invadercorps.poketest.data.repository.APIClient
import com.invadercorps.poketest.data.repository.model.PokeList
import com.invadercorps.poketest.data.repository.model.StatefulData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PokemonListViewModel(application: Application) : AndroidViewModel(application) {

    private val _pokemonList: MutableLiveData<StatefulData<PokeList>> =
            MutableLiveData<StatefulData<PokeList>>(StatefulData.Empty())
    val pokemonList: LiveData<StatefulData<PokeList>> = _pokemonList

    fun getPokemonList() {
        _pokemonList.value = StatefulData.Loading()
        val pokemonList = APIClient.getClient().getPokemonList()

        //we use handler to visualize loading
        Handler(Looper.getMainLooper()).postDelayed({
            pokemonList.enqueue(object : Callback<PokeList>{
            override fun onResponse(call: Call<PokeList>, response: Response<PokeList>) {
                response.body()?.let { _pokemonList.value = StatefulData.Success(it) } }

            override fun onFailure(call: Call<PokeList>, t: Throwable) {
                _pokemonList.value = StatefulData.Error()
            }
        })}, 2000)

    }

}