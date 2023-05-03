package com.example.chefslaundary


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class TermsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_terms)
    }
    fun onbtnTermSubmitClicked(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}