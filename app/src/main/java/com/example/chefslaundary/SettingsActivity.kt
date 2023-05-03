package com.example.chefslaundary

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AppCompatDelegate

class SettingsActivity : AppCompatActivity() {
    private lateinit var switcher: Switch
    private var nightMODE = false
    private lateinit var sharedPreferences2: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        supportActionBar?.hide()

        switcher = findViewById(R.id.sw_dark)
        val myTextView = findViewById<TextView>(R.id.tv_setting_title)
        val myTextView2 = findViewById<TextView>(R.id.sound_text)
        val myTextView3 = findViewById<TextView>(R.id.difficulty_level_text)
        val myTermAndCondition = findViewById<Button>(R.id.terms_and_conditions_button)



        sharedPreferences2 = getSharedPreferences("MODE", Context.MODE_PRIVATE)
        nightMODE = sharedPreferences2.getBoolean("night", false)

        if (nightMODE) {
            switcher.isChecked = true
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            myTextView.setTextColor(Color.WHITE)
            myTextView2.setTextColor(Color.WHITE)
            myTextView3.setTextColor(Color.WHITE)
            myTermAndCondition.setTextColor(Color.BLACK)
        }

        switcher.setOnClickListener {
            if (nightMODE) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                editor = sharedPreferences2.edit()
                editor.putBoolean("night", false)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                editor = sharedPreferences2.edit()
                editor.putBoolean("night", true)
            }
            editor.apply()
        }
    }

    fun onterms_and_conditions_buttonClicked(view: View) {
        val intent = Intent(this, TermsActivity::class.java)
        startActivity(intent)
    }

}
