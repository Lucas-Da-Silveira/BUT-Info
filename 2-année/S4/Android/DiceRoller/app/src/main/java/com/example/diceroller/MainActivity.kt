package com.example.diceroller

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

/**
 * MainActivity is the entry point of this application.
 * This activity allows the user to roll a dice and view the result on the screen.
 */
class MainActivity : AppCompatActivity() {

    /**
     * This is the first callback in the lifecycle of an activity.
     * The onCreate method is called when the activity is first created.
     */
    @SuppressLint("SetTextI18n", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Reference to the roll button on the screen
        val rollButton: Button = findViewById(R.id.button)

        val textView2: TextView = findViewById(R.id.textView2)
        textView2.text = "Dice 1"

        val textView3: TextView = findViewById(R.id.textView3)
        textView3.text = "Dice 2"

        // Set a click listener on the roll button to roll the dice when the user taps the button
        rollButton.setOnClickListener { rollDice(); rollDice2() }
    }

    /**
     * This method is called when the roll button is clicked.
     * It creates a new Dice object, rolls it and updates the image on the screen based on the result of the roll.
     */
    private fun rollDice() {
        // Create new Dice object with 6 sides and roll it
        val dice = Dice(6)
        val diceRoll = dice.roll()

        // Reference to the dice image on the screen
        val diceImage: ImageView = findViewById(R.id.imageView)

        // Determine which drawable resource ID to use based on the dice roll
        val drawableResource = when (diceRoll) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }

        // Update the dice image with the appropriate drawable
        diceImage.setImageResource(drawableResource)

        // Update the content description for the dice image
        diceImage.contentDescription = diceRoll.toString()
    }

    private fun rollDice2() {
        // Create new Dice object with 6 sides and roll it
        val dice = Dice(6)
        val diceRoll = dice.roll()

        // Reference to the dice image on the screen
        val diceImage: ImageView = findViewById(R.id.imageView2)

        // Determine which drawable resource ID to use based on the dice roll
        val drawableResource = when (diceRoll) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }

        // Update the dice image with the appropriate drawable
        diceImage.setImageResource(drawableResource)

        // Update the content description for the dice image
        diceImage.contentDescription = diceRoll.toString()
    }
}

/**
 * Dice is a simple class that represents a dice with a certain number of sides.
 * It has a roll method that can be used to simulate rolling the dice.
 */
class Dice(private val numSides: Int) {

    /**
     * This method simulates rolling the dice.
     * It returns a random number between 1 and the number of sides the dice has.
     */
    fun roll(): Int {
        return (1..numSides).random()
    }
}