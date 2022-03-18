package com.invadercorps.poketest.data.repository.model.pokemondetails.types

import com.fasterxml.jackson.annotation.JsonProperty

data class TypeProperty (
    @JsonProperty(value = "slot", required = true)
    val slot : Int,
    @JsonProperty(value = "type", required = true)
    var type : Type

        )