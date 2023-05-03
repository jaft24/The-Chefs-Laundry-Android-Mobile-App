package com.example.chefslaundary


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        //val name = intent.getStringExtra("name")
        var score: Int = intent.getIntExtra("score", 0)
        var pName = intent.getStringExtra("finalName")

        val textView = findViewById<TextView>(R.id.tv_Answer)
        textView.text = score.toString()

        val hs = findViewById<Button>(R.id.btn_viewHighScore)
        hs.setOnClickListener(){
            val intent = Intent(this, HighScoreActivity::class.java)
            intent.putExtra("finalName", pName)
            intent.putExtra("score", score)
            startActivity(intent)
        }


    }

    fun onplayAgainButtonClicked(view: View) {
        val intent = Intent(this, NameActivity::class.java)
        startActivity(intent)
    }

//    fun onViewHighScoreButtonClicked(view: View, pName :String, score : String) {
//        val intent = Intent(this, HighScoreActivity::class.java)
//        intent.putExtra("finalName", pName)
//        intent.putExtra("score", score)
//        startActivity(intent)
//    }

    fun onexitButtonClicked(view: View) {
        finishAffinity()
    }
}
