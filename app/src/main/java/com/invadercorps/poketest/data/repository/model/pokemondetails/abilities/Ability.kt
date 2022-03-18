package com.invadercorps.poketest.data.repository.model.pokemondetails.abilities

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown=true)
data class Ability (
    @JsonProperty(value = "name", required = true)
    val name: String,
        )