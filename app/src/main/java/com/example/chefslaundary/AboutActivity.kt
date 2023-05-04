package com.example.chefslaundary

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class AboutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        val helpPage = findViewById<Button>(R.id.helpPageBtnAboutUs)
        helpPage.setOnClickListener{
            val intent = Intent(this, FaqActivity::class.java)
            startActivity(intent)
        }

        // Find this button
        val home = findViewById<Button>(R.id.homeBtnAboutUs)
        home.setOnClickListener(){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }
}