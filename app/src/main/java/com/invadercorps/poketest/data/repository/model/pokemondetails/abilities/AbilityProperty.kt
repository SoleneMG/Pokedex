package com.invadercorps.poketest.data.repository.model.pokemondetails.abilities

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown=true)
data class AbilityProperty(
    @JsonProperty(value = "ability", required = true)
    var type : Ability
)
