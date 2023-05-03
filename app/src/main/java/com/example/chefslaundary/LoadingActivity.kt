package com.example.chefslaundary

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class LoadingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loading)

        val playerName = intent.getStringExtra("name")

        Handler().postDelayed({
            // Start main activity after 3 seconds
            val intent = Intent(this, GameActivity::class.java)
            intent.putExtra("playerName", playerName)
            startActivity(intent)
            finish()
        }, 3000)
    }
}