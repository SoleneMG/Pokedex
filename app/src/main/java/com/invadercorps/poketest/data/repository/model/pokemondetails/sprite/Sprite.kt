package com.invadercorps.poketest.data.repository.model.pokemondetails.sprite

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown=true)
data class Sprite (
    @JsonProperty(value = "front_default", required = true)
    val frontDrawing : String
)