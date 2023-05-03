package com.example.chefslaundary

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var volumeOnButton: FloatingActionButton
    private lateinit var volumeOffButton: FloatingActionButton

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    private var isPlaying = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize media player with background music
        mediaPlayer = MediaPlayer.create(this, R.raw.chefmusic)
        mediaPlayer.isLooping = true
        mediaPlayer.setVolume(0.5f, 0.5f)

        // Initialize volume buttons
        volumeOnButton = findViewById(R.id.volume_on)
        volumeOffButton = findViewById(R.id.volume_off)
        volumeOnButton.setOnClickListener { turnOnMusic() }
        volumeOffButton.setOnClickListener { turnOffMusic() }

        sharedPreferences = getSharedPreferences("MUSIC", Context.MODE_PRIVATE)
        isPlaying = sharedPreferences.getBoolean("isPlaying", false)

        if (isPlaying) {
            mediaPlayer.start()
            volumeOnButton.visibility = View.GONE
            volumeOffButton.visibility = View.VISIBLE
        }

        // Set initial state of volume buttons
        volumeOnButton.visibility = View.VISIBLE
        volumeOffButton.visibility = View.GONE

        var newGame = findViewById<Button>(R.id.bt_Newgame)
        newGame.setOnClickListener {
            val intent = Intent(this, NameActivity::class.java)
            startActivity(intent)
        }

        var faqButton = findViewById<Button>(R.id.bt_faq)
        var aboutButton = findViewById<Button>(R.id.bt_about)
        var highScore = findViewById<Button>(R.id.bt_Highscore2)

        faqButton.setOnClickListener {
            val intent1 = Intent(this, FaqActivity::class.java)
            startActivity(intent1)
        }
        aboutButton.setOnClickListener {
            val intent2 = Intent(this, AboutActivity::class.java)
            startActivity(intent2)
        }
        highScore.setOnClickListener {
            val intent7 = Intent(this, HighScoreActivity::class.java)
            startActivity(intent7)
        }


    }



    private fun turnOnMusic() {
        mediaPlayer.start()
        volumeOnButton.visibility = View.GONE
        volumeOffButton.visibility = View.VISIBLE
    }

    private fun turnOffMusic() {
        mediaPlayer.pause()
        volumeOnButton.visibility = View.VISIBLE
        volumeOffButton.visibility = View.GONE
    }


    override fun onPause() {
        super.onPause()
        editor = sharedPreferences.edit()
        editor.putBoolean("isPlaying", mediaPlayer.isPlaying)
        editor.apply()
    }


    override fun onResume() {
        super.onResume()
        isPlaying = sharedPreferences.getBoolean("isPlaying", false)
        if (isPlaying) {
            volumeOnButton.visibility = View.GONE
            volumeOffButton.visibility = View.VISIBLE
        } else {
            volumeOnButton.visibility = View.VISIBLE
            volumeOffButton.visibility = View.GONE
        }
    }

//    fun onNew(view: View) {
//        val intent = Intent(this, SettingsActivity::class.java)
//        startActivity(intent)
//    }

    fun onNewGameButtonClicked(view: View){
        val intent = Intent(this, NameActivity::class.java)
        startActivity(intent)
    }


    fun onSettingsButtonClicked(view: View){
        val intent = Intent(this, SettingsActivity::class.java)
        startActivity(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.stop()
        mediaPlayer.release()
    }
}

