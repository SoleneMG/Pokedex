package com.invadercorps.poketest.data.repository.model

import com.fasterxml.jackson.annotation.JsonProperty

data class PokeList(

        @JsonProperty("count") var count: Int? = null,
        @JsonProperty("next") var next: String? = null,
        @JsonProperty("previous") var previous: String? = null,
        @JsonProperty("results") var results: ArrayList<Pokemon> = arrayListOf()

)