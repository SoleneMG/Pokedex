package com.invadercorps.poketest.module.pokemondetails.view

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.invadercorps.poketest.R

class PokemonDetailsActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_SELECTED_POKEMON_ID = "EXTRA_SELECTED_POKEMON_ID"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pokemon_details_activity)

        /*
         When we use landscape mode, we don't want this activity anymore because
         PokemonDetailsFragment is shown from HomeActivity
         */
        if(resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE)
            finish()
    }
}