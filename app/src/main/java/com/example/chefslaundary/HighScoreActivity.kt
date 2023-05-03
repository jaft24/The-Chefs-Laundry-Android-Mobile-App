package com.example.chefslaundary

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.EditText
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView

data class HighScore(val name: String, val score: Int)
class HighScoreActivity : AppCompatActivity() {
    private val highScores = mutableListOf(
        HighScore("John", 100),
        HighScore("Daniel", 90),
        HighScore("Bob", 70),
        HighScore("Alex", 60)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_high_score)

        val highScoreTable = findViewById<TableLayout>(R.id.high_score_table)

        val name = intent.getStringExtra("finalName")
        val score = intent.getIntExtra("score", 0)

        if (name != null) {
            highScores.add(HighScore(name, score))
        }

        highScores.sortByDescending { it.score }


        for (score in highScores) {
            val row = TableRow(this)
            val nameView = TextView(this).apply {
                text = score.name
                textSize = 16f
                gravity = Gravity.START
            }
            val scoreView = TextView(this).apply {
                text = score.score.toString()
                textSize = 16f
                gravity = Gravity.END
            }
            row.addView(nameView)
            row.addView(scoreView)
            highScoreTable.addView(row)
        }


        val backButton = findViewById<Button>(R.id.backBtn)
        backButton.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }


}


