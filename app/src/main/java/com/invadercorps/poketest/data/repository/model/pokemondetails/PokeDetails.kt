package com.invadercorps.poketest.data.repository.model.pokemondetails

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.invadercorps.poketest.data.repository.model.pokemondetails.abilities.Ability
import com.invadercorps.poketest.data.repository.model.pokemondetails.abilities.AbilityProperty
import com.invadercorps.poketest.data.repository.model.pokemondetails.sprite.Sprite
import com.invadercorps.poketest.data.repository.model.pokemondetails.types.TypeProperty

@JsonIgnoreProperties(ignoreUnknown=true)
data class PokeDetails(

    @JsonProperty(value = "abilities", required = true)
    var abilities : ArrayList<AbilityProperty> = arrayListOf(),
    @JsonProperty(value = "name", required = true)
    var name : String,
    @JsonProperty(value = "sprites", required = true)
    var sprite : Sprite,
    @JsonProperty(value = "types", required = true)
    var types : ArrayList<TypeProperty> = arrayListOf()
)
