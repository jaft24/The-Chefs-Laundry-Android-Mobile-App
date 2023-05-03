package com.example.chefslaundary

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast

class NameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_name)
    }
    fun onSubmitClicked(view: View) {
        val name = findViewById<EditText>(R.id.etn_enterName).text.toString()

        if (name == ""){

            Toast.makeText(this,"Please use a valid name.",Toast.LENGTH_SHORT).show()

        } else {
            val intent = Intent(this, LoadingActivity::class.java)
            intent.putExtra("name", name)
            startActivity(intent)
        }
    }
}