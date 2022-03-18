package com.invadercorps.poketest.data.repository

import com.invadercorps.poketest.data.repository.model.PokeList
import com.invadercorps.poketest.data.repository.model.pokemondetails.PokeDetails
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PokeService {

    @GET("pokemon?limit=151")
    fun getPokemonList(): Call<PokeList>

    @GET("pokemon/{id}")
    fun getPokemonDetails(@Path("id") id : String?) : Call<PokeDetails>

}
