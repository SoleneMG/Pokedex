package com.invadercorps.poketest.module.splash.view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.invadercorps.poketest.R
import com.invadercorps.poketest.module.home.view.HomeActivity


class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_activity)
        Glide
            .with(this)
            .load(R.drawable.poke_loader)
            .into(findViewById(R.id.loader))

        //use handler to have time to visualize splash
        Handler(mainLooper).postDelayed({
            finish()
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }, 3000)
    }

}