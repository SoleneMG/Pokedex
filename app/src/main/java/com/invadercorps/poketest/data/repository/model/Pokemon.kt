package com.invadercorps.poketest.data.repository.model

import com.fasterxml.jackson.annotation.JsonProperty

data class Pokemon(

        @JsonProperty(value = "name", required = true)
        val name: String,
        @JsonProperty(value = "url", required = true)
        val url: String,

        ) {
    val id
        get() : String {
            return url.split("/").takeLast(2).first()
        }
}