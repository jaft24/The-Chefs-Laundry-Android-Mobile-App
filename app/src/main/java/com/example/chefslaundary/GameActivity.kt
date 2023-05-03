package com.example.chefslaundary

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import pl.droidsonroids.gif.GifImageView
import java.lang.System.exit

class GameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        // Declaring the Views and Buttons initially.
        // Ingredient Buttons
        val breadButton = findViewById<Button>(R.id.breadButton)
        val turkeyButton = findViewById<Button>(R.id.turkeyButton)
        val lettuceButton = findViewById<Button>(R.id.lettuceButton)
        val chickenButton = findViewById<Button>(R.id.chickenButton)
        val pepersButton = findViewById<Button>(R.id.pepersButton)
        val baconButton = findViewById<Button>(R.id.baconButton)
        val onionButton = findViewById<Button>(R.id.onionButton)
        val cheeseButton = findViewById<Button>(R.id.cheeseButton)
        val tomatoButton = findViewById<Button>(R.id.tomatoButton)
        val ketchupButton = findViewById<Button>(R.id.ketchupButton)
        val mustardButton = findViewById<Button>(R.id.mustardButton)
        val mayoButton = findViewById<Button>(R.id.mayoButton)
        val ingredientButtons = listOf<Button>(
            breadButton,
            turkeyButton, baconButton, chickenButton, cheeseButton,
            pepersButton, tomatoButton, onionButton, lettuceButton,
            ketchupButton, mustardButton, mayoButton
        )

        // Action Buttons (Only three of them no need to create an array)
        val submitButton = findViewById<Button>(R.id.submitButton)
        val trashButton = findViewById<Button>(R.id.trashButton)
        val endButton = findViewById<Button>(R.id.endGameButton)

        // Table select Buttons
        val table1Button = findViewById<Button>(R.id.order1Button)
        val table2Button = findViewById<Button>(R.id.order2Button)
        val table3Button = findViewById<Button>(R.id.order3Button)
        val table4Button = findViewById<Button>(R.id.order4Button)
        val tables = listOf<Button>(table1Button, table2Button, table3Button, table4Button)

        // Image views
        val ivUBread = findViewById<ImageView>(R.id.ivUBread)
        val ivMustard = findViewById<ImageView>(R.id.ivMustard)
        val ivKetchup = findViewById<ImageView>(R.id.ivKetchup)
        val ivPepers = findViewById<ImageView>(R.id.ivPepers)
        val ivOnion = findViewById<ImageView>(R.id.ivOnion)
        val ivTomato = findViewById<ImageView>(R.id.ivTomato)
        val ivLettuce = findViewById<ImageView>(R.id.ivLettuce)
        val ivCheese = findViewById<ImageView>(R.id.ivCheese)
        val ivTurkey = findViewById<ImageView>(R.id.ivTurkey)
        val ivBacon = findViewById<ImageView>(R.id.ivBacon)
        val ivChicken = findViewById<ImageView>(R.id.ivChicken)
        val ivMayo = findViewById<ImageView>(R.id.ivMayo)

        //Iv should be invisible initially
        val ingredientViews = listOf<ImageView>(
            ivUBread,
            ivTurkey, ivBacon, ivChicken, ivCheese,
            ivPepers, ivTomato, ivOnion, ivLettuce,
            ivKetchup, ivMustard, ivMayo
        )
        for (everyView in ingredientViews) {
            clearIV(everyView)
        }

        // order text views
        val tvOrder1 = findViewById<TextView>(R.id.tvOrder1)
        val tvOrder2 = findViewById<TextView>(R.id.tvOrder2)
        val tvOrder3 = findViewById<TextView>(R.id.tvOrder3)
        val tvOrder4 = findViewById<TextView>(R.id.tvOrder4)
        val orders = mutableListOf<TextView>(tvOrder1, tvOrder2, tvOrder3, tvOrder4)
        //All Orders should be unselected initially
        for (everyOrder in orders) {
            clearOrders(everyOrder)
        }

        // pointer Gif
        val table1Pointer = findViewById<GifImageView>(R.id.table1Pointer)
        val table2Pointer = findViewById<GifImageView>(R.id.table2Pointer)
        val table3Pointer = findViewById<GifImageView>(R.id.table3Pointer)
        val table4Pointer = findViewById<GifImageView>(R.id.table4Pointer)
        val pointers =
            listOf<GifImageView>(table1Pointer, table2Pointer, table3Pointer, table4Pointer)
        //All Pointers should be empty initially, unless order is selected.
        for (everyPointer in pointers) {
            clearPointers(everyPointer)
        }

        // Set up the functionalities for the Ingredient Buttons
        ingredientViews.zip(ingredientButtons).forEach { pair ->
            connectButtonView(pair.component1(), pair.component2())
        }

        //Set up the functionalities for table and pointers with selecting a Buttons
        for (i in 0..3) {
            oneOrderSelect(orders.get(i), orders, tables.get(i), pointers, pointers.get(i))
        }

        // Set up the Orders from the other class
        var firstOrderText: String = OrderList().order1
        var secondOrderText: String = OrderList().order2
        var thirdOrderText: String = OrderList().order3
        var fourthOrderText: String = OrderList().order4
        var fifthOrderText: String = OrderList().order5
        var sixthOrderText: String = OrderList().order6
        var seventhOrderText: String = OrderList().order7
        var eighthOrderText: String = OrderList().order8

        var orderText =
            mutableListOf<String>(firstOrderText, secondOrderText, thirdOrderText, fourthOrderText)
        var replaceableOrderText =
            mutableListOf<String>(fifthOrderText, sixthOrderText, seventhOrderText, eighthOrderText)

        var firstOrderId = OrderList().order1Id
        var secondOrderId = OrderList().order2Id
        var thirdOrderId = OrderList().order3Id
        var fourthOrderId = OrderList().order4Id
        var fifthOrderId = OrderList().order5Id
        var sixthOrderId = OrderList().order6Id
        var seventhOrderId = OrderList().order7Id
        var eighthOrderId = OrderList().order8Id

        var orderId =
            mutableListOf<List<Int>>(firstOrderId, secondOrderId, thirdOrderId, fourthOrderId)
        var replaceableOrderId =
            mutableListOf<List<Int>>(fifthOrderId, sixthOrderId, seventhOrderId, eighthOrderId)

        // Declare the Input Combination
        var inputIngredientId = mutableListOf<Int>()
        var gameIngredientId = mutableListOf<Int>()
        var replaceableOrderTextView: TextView
        var replaceableOrderIdNum: MutableList<Int>

        // Declare new score and set as view
        var scoreTv = findViewById<TextView>(R.id.scoreTV)
        var score: Int = 50;
        scoreTv.text = score.toString()

        // Declare Decrease and Increase Gif Images
        val upGif = findViewById<GifImageView>(R.id.scoreIncrease)
        val downGif = findViewById<GifImageView>(R.id.scoreDecrease)
        val down2Gif = findViewById<GifImageView>(R.id.scoreDecreaseTwo)
        upGif.visibility = View.INVISIBLE
        downGif.visibility = View.INVISIBLE
        down2Gif.visibility = View.INVISIBLE

        var playtv = findViewById<TextView>(R.id.playerName)
        val playerName = intent.getStringExtra("playerName")
        playtv.text = playerName


        // Timer Code
        val pDelay: Long = 10000
        val mTimerTextView: TextView = findViewById(R.id.newOrderTime)
        val mCountDownTimer = object : CountDownTimer(pDelay, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val secondsRemaining1 = (millisUntilFinished / 1000).toString()
                mTimerTextView.text = secondsRemaining1
            }

            override fun onFinish() {
                mTimerTextView.text = "0"

                // Start the timer for the Game

                val gDelay: Long = 100000
                val gTimerTextView: TextView = findViewById(R.id.gameTimeleft)
                val gCountDownTimer = object : CountDownTimer(gDelay, 1000) {
                    override fun onTick(millisUntilFinished: Long) {
                        val secondsRemaining2 = (millisUntilFinished / 1000).toString()
                        gTimerTextView.text = secondsRemaining2
                    }

                    override fun onFinish() {
                        mTimerTextView.text = "0"
                        var intent = Intent(this@GameActivity, ResultActivity::class.java)
                        intent.putExtra("finalName", playerName)
                        intent.putExtra("score", score)
                        startActivity(intent)

                        // End the Game when the timer ends


                    }
                }
                gCountDownTimer.start()


            }
        }
        mCountDownTimer.start()


        // Declaring a delay
        // in milli-seconds
        val mDelay: Long = 1000

        // Set the Textviews to the Orders
        for (i in 0..3) {

            mStartCounter(mDelay)
            Handler(Looper.getMainLooper()).postDelayed({

                setOrdertoTv(orders.get(i), orderText.get(i))

            }, (i * 3) * mDelay)
        }


        // Trash Button Activity
        trashButton.setOnClickListener {

            for (i in 0..11) {
                if (ingredientViews.get(i).isVisible) {
                    for (everyView in ingredientViews) {
                        clearIV(everyView)
                    }
                    inputIngredientId.clear()
                    scorePenalty(score, scoreTv, upGif, downGif, down2Gif)
                    score = (scoreTv.text as String).toInt()
                    if (score <= 0) {
                        val intent = Intent(this, ResultActivity::class.java)
                        intent.putExtra("finalName", playerName)
                        intent.putExtra("score", score)
                        startActivity(intent)
                    }
                }
            }

        }

        // Replacer and Game life Integer
        var replaceQueue: Int = 0
        var gameLife: Int = 4


        // Submit Button Activity
        submitButton.setOnClickListener {
            inputIngredientId.clear()



            for (i in 1..12) {
                if (ingredientViews.get(i - 1).isVisible) {
                    inputIngredientId.add(i)
                }
            }



            if (table1Pointer.isVisible) {

                for (everyItem in firstOrderId) {
                    gameIngredientId.add(everyItem)
                }

            } else if (table2Pointer.isVisible) {
                for (everyItem in secondOrderId) {
                    gameIngredientId.add(everyItem)
                }

            } else if (table3Pointer.isVisible) {
                for (everyItem in thirdOrderId) {
                    gameIngredientId.add(everyItem)
                }

            } else if (table4Pointer.isVisible) {
                for (everyItem in fourthOrderId) {
                    gameIngredientId.add(everyItem)
                }

            } else {
                Toast.makeText(this, "Please select a table first", Toast.LENGTH_LONG).show()
            }

            // Compare and Post the results
            if (inputIngredientId.size != 0 && gameIngredientId.size != 0) {


                // Compare and provide result
                gameAlgo(
                    inputIngredientId,
                    gameIngredientId,
                    score,
                    scoreTv,
                    upGif,
                    downGif,
                    down2Gif
                )
                score = (scoreTv.text as String).toInt()

                if (score <= 0) {

                    val intent = Intent(this, ResultActivity::class.java)
                    intent.putExtra("finalName", playerName)
                    intent.putExtra("score", score)
                    startActivity(intent)
                }



                if (inputIngredientId == gameIngredientId && replaceQueue < 4) {

                    // Clear submited table order
                    for (everyView in ingredientViews) {
                        clearIV(everyView)
                    }

                    inputIngredientId.clear();
                    gameIngredientId.clear();

                    if (table1Pointer.isVisible) {
                        firstOrderId = replaceableOrderId.get(replaceQueue) as MutableList<Int>
                        firstOrderText = replaceableOrderText.get(replaceQueue)
                        tvOrder1.text = "Table 1 is waiting for order."

                        val pDelay: Long = 10000
                        val mTimerTextView: TextView = findViewById(R.id.newOrderTime)
                        val mCountDownTimer = object : CountDownTimer(pDelay, 1000) {
                            override fun onTick(millisUntilFinished: Long) {
                                val secondsRemaining = (millisUntilFinished / 1000).toString()
                                mTimerTextView.text = secondsRemaining
                            }

                            override fun onFinish() {
                                mTimerTextView.text = "0"
                            }
                        }
                        mCountDownTimer.start()

                        setOrdertoTv(tvOrder1, firstOrderText)
                        replaceQueue++

                        for (everyPointer in pointers) {
                            clearPointers(everyPointer)
                        }
                        for (everyOrder in orders) {
                            clearOrders(everyOrder)
                        }
                    } else if (table2Pointer.isVisible) {
                        secondOrderId = replaceableOrderId.get(replaceQueue) as MutableList<Int>
                        secondOrderText = replaceableOrderText.get(replaceQueue)
                        tvOrder2.text = "Table 2 is waiting for order."

                        val pDelay: Long = 10000
                        val mTimerTextView: TextView = findViewById(R.id.newOrderTime)
                        val mCountDownTimer = object : CountDownTimer(pDelay, 1000) {
                            override fun onTick(millisUntilFinished: Long) {
                                val secondsRemaining = (millisUntilFinished / 1000).toString()
                                mTimerTextView.text = secondsRemaining
                            }

                            override fun onFinish() {
                                mTimerTextView.text = "0"
                            }
                        }
                        mCountDownTimer.start()

                        setOrdertoTv(tvOrder2, secondOrderText)
                        replaceQueue++

                        for (everyPointer in pointers) {
                            clearPointers(everyPointer)
                        }
                        for (everyOrder in orders) {
                            clearOrders(everyOrder)
                        }
                    } else if (table3Pointer.isVisible) {
                        thirdOrderId = replaceableOrderId.get(replaceQueue) as MutableList<Int>
                        thirdOrderText = replaceableOrderText.get(replaceQueue)
                        tvOrder3.text = "Table 3 is waiting for order."

                        val pDelay: Long = 10000
                        val mTimerTextView: TextView = findViewById(R.id.newOrderTime)
                        val mCountDownTimer = object : CountDownTimer(pDelay, 1000) {
                            override fun onTick(millisUntilFinished: Long) {
                                val secondsRemaining = (millisUntilFinished / 1000).toString()
                                mTimerTextView.text = secondsRemaining
                            }

                            override fun onFinish() {
                                mTimerTextView.text = "0"
                            }
                        }
                        mCountDownTimer.start()

                        setOrdertoTv(tvOrder3, thirdOrderText)
                        replaceQueue++

                        for (everyPointer in pointers) {
                            clearPointers(everyPointer)
                        }
                        for (everyOrder in orders) {
                            clearOrders(everyOrder)
                        }
                    } else if (table4Pointer.isVisible) {
                        fourthOrderId = replaceableOrderId.get(replaceQueue) as MutableList<Int>
                        fourthOrderText = replaceableOrderText.get(replaceQueue)
                        tvOrder4.text = "Table 4 is waiting for order."

                        val pDelay: Long = 10000
                        val mTimerTextView: TextView = findViewById(R.id.newOrderTime)
                        val mCountDownTimer = object : CountDownTimer(pDelay, 1000) {
                            override fun onTick(millisUntilFinished: Long) {
                                val secondsRemaining = (millisUntilFinished / 1000).toString()
                                mTimerTextView.text = secondsRemaining
                            }

                            override fun onFinish() {
                                mTimerTextView.text = "0"
                            }
                        }
                        mCountDownTimer.start()

                        setOrdertoTv(tvOrder4, fourthOrderText)
                        replaceQueue++

                        for (everyPointer in pointers) {
                            clearPointers(everyPointer)
                        }
                        for (everyOrder in orders) {
                            clearOrders(everyOrder)
                        }
                    }


                } else if (inputIngredientId == gameIngredientId && replaceQueue >= 4) {


                    inputIngredientId.clear();
                    gameIngredientId.clear();

                    if (table1Pointer.isVisible) {

                        firstOrderText = "No More orders from Table 1"
                        setOrdertoTv(tvOrder1, firstOrderText)
                        gameLife--


                        for (everyPointer in pointers) {
                            clearPointers(everyPointer)
                        }
                        for (everyOrder in orders) {
                            clearOrders(everyOrder)
                        }
                    } else if (table2Pointer.isVisible) {
                        secondOrderText = "No More orders from Table 2"
                        setOrdertoTv(tvOrder2, secondOrderText)
                        gameLife--

                        for (everyPointer in pointers) {
                            clearPointers(everyPointer)
                        }
                        for (everyOrder in orders) {
                            clearOrders(everyOrder)
                        }
                    } else if (table3Pointer.isVisible) {
                        thirdOrderText = "No More orders from Table 3"
                        setOrdertoTv(tvOrder3, thirdOrderText)
                        gameLife--

                        for (everyPointer in pointers) {
                            clearPointers(everyPointer)
                        }
                        for (everyOrder in orders) {
                            clearOrders(everyOrder)
                        }
                    } else if (table4Pointer.isVisible) {
                        fourthOrderText = "No More orders from Table 4"
                        setOrdertoTv(tvOrder4, fourthOrderText)
                        gameLife--

                        for (everyPointer in pointers) {
                            clearPointers(everyPointer)
                        }
                        for (everyOrder in orders) {
                            clearOrders(everyOrder)
                        }
                    }


                    if (gameLife == 0) {

                        val intent = Intent(this, ResultActivity::class.java)
                        intent.putExtra("finalName", playerName)
                        intent.putExtra("score", score)
                        startActivity(intent)


                    }


                } else {
                    inputIngredientId.clear();
                    gameIngredientId.clear();
                }


            }


        }

        endButton.setOnClickListener {
            // Ask for confirmation and end the game
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Quit?")
            builder.setMessage("Are you sure want to quit cooking?")
            builder.setPositiveButton("Ok") { _, _ ->
                Toast.makeText(this, "Hasta Lavista Baker", Toast.LENGTH_LONG).show()
                val intent3 = Intent(this, MainActivity::class.java)
                startActivity(intent3)
                //finish()
            }
            builder.setNegativeButton("Cancel", null)
            builder.show()


        }


    }

    fun clearIV(Ingredient: ImageView) {
        Ingredient.visibility = View.INVISIBLE;
    }

    fun connectButtonView(ivIngredient: ImageView, buttonIngredient: Button) {
        buttonIngredient.setOnClickListener {
            if (ivIngredient.isInvisible) {
                ivIngredient.visibility = View.VISIBLE; } else if (ivIngredient.isVisible) {
                ivIngredient.visibility = View.INVISIBLE; }
        }
    }

    fun clearOrders(orders: TextView) {
        orders.setBackgroundColor(Color.YELLOW);
    }

    fun clearPointers(pointer: GifImageView) {
        pointer.visibility = View.INVISIBLE;
    }

    fun oneOrderSelect(
        currentTv: TextView,
        orderList: List<TextView>,
        buttonIngredient: Button,
        pointerList: List<GifImageView>,
        pointer: GifImageView,
    ) {
        buttonIngredient.setOnClickListener {
            orderList.zip(pointerList).forEach { pair ->
                clearOrders(pair.component1())
                clearPointers(pair.component2())
            }
            pointer.visibility = View.VISIBLE;
            currentTv.setBackgroundColor(Color.rgb(255, 195, 0))
        }
    }

    fun setOrdertoTv(tvOrder: TextView, orderText: String) {
        val mDelay: Long = 10000
        mStartCounter(mDelay)
        Handler(Looper.getMainLooper()).postDelayed({


            tvOrder.text = orderText

        }, mDelay)
    }

    fun scorePenalty(
        score: Int,
        scoreDisplay: TextView,
        upGif: GifImageView,
        downGif: GifImageView,
        down2Gif: GifImageView
    ) {
        var funScore = score
        Toast.makeText(this, "Trashing Ingredients Costs! 5 Points Taken", Toast.LENGTH_LONG).show()
        funScore -= 5
        if (funScore <= 0) {
            funScore = 0
        }
        scoreDisplay.text = funScore.toString()
        upGif.visibility = View.INVISIBLE
        down2Gif.visibility = View.INVISIBLE
        downGif.visibility = View.VISIBLE

    }

    fun gameAlgo(
        inputIdList: MutableList<Int>,
        gameIdList: MutableList<Int>,
        score: Int,
        scoreDisplay: TextView,
        upGif: GifImageView,
        downGif: GifImageView,
        down2Gif: GifImageView
    ) {
        if (inputIdList == gameIdList) {
            var funScore = score
            Toast.makeText(this, "Delicious. Keep Cooking, 10 Points Added", Toast.LENGTH_LONG)
                .show()
            funScore += 10
            scoreDisplay.text = funScore.toString()
            downGif.visibility = View.INVISIBLE
            down2Gif.visibility = View.INVISIBLE
            upGif.visibility = View.VISIBLE
        } else {
            var funScore = score
            Toast.makeText(
                this,
                "Customers are Upset! Wrong Order, 10 Points Deducted",
                Toast.LENGTH_LONG
            ).show()
            funScore -= 10
            if (funScore <= 0) {
                funScore = 0
            }
            scoreDisplay.text = funScore.toString()
            upGif.visibility = View.INVISIBLE
            downGif.visibility = View.VISIBLE
            down2Gif.visibility = View.VISIBLE
        }

    }
//
//    fun clearOrder(Order: List<TextView>, pointer: List<GifImageView>, gameOrderID: List<List<Int>>, orderText: List<String>) {
//        for (everyPointer in pointer){
//            clearPointers(everyPointer)
//        }
//
//
//
//    }
//
//    fun replaceOrderId(gameOrderID: List<List<Int>>, orderText: List<String>, Order: List<TextView>) {
//
//    }


    private fun mStartCounter(delay: Long) {
        Thread {
            for (i in 0..delay / 1000) {
                runOnUiThread {
                }
                Thread.sleep(1000)
            }
        }.start()
    }


}