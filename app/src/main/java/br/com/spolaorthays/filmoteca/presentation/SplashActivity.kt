package br.com.spolaorthays.filmoteca.presentation

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import br.com.spolaorthays.filmoteca.databinding.ActivitySplashBinding
import br.com.spolaorthays.filmoteca.movie.presentation.MainActivity
import dagger.android.support.DaggerAppCompatActivity
import java.util.Timer
import java.util.TimerTask

@SuppressLint("CustomSplashScreen")
class SplashActivity : DaggerAppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Timer().schedule(object : TimerTask() {
            override fun run() {
                val intent = Intent(this@SplashActivity, MainActivity::class.java)
                startActivity(intent)
            }
        }, 2000L)
    }
}