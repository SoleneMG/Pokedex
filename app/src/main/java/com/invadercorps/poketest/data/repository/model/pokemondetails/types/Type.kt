package com.invadercorps.poketest.data.repository.model.pokemondetails.types

import com.fasterxml.jackson.annotation.JsonProperty

data class Type(
    @JsonProperty(value = "name", required = true)
    val name: String,
    @JsonProperty(value = "url", required = true)
    val url: String,
)