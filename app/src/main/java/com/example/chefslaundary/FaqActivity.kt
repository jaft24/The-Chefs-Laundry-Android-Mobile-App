package com.example.chefslaundary

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class FaqActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_faq)

        val aboutUs = findViewById<Button>(R.id.aboutUsBtnHelp)
        aboutUs.setOnClickListener(){
            val intent = Intent(this, FaqActivity::class.java)
            startActivity(intent)
        }
        val home = findViewById<Button>(R.id.homeBtnHelpPage)
        home.setOnClickListener(){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}