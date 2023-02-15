package com.daviiid99.sum_petit

import android.media.MediaPlayer
import android.os.Bundle
import android.provider.MediaStore.Audio.Media
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.w3c.dom.Text
import java.io.BufferedReader

class MainActivity : AppCompatActivity() {

    lateinit var first_operator : TextView
    lateinit var second_operator : TextView
    lateinit var symbol : TextView
    lateinit var first_result : Button
    lateinit var second_result : Button
    lateinit var third_result : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Start game onCreate
        var game = Logic()
        game = startGame(game)

        // Current operation widgets assignments
        first_operator = findViewById(R.id.first_operand_text)
        second_operator = findViewById(R.id.second_operand_text)
        symbol = findViewById(R.id.symbol_text)

        // Results widgets assignments
        first_result = findViewById(R.id.first_button)
        second_result = findViewById(R.id.second_button)
        third_result = findViewById(R.id.third_button)

        // Assign results to widgets
        widgetsAssignment(game)

        // A method to check what buttons is being pressed
        first_result.setOnClickListener { checkButtonPress(game, first_result.text.toString()) }
        second_result.setOnClickListener { checkButtonPress(game, second_result.text.toString()) }
        third_result.setOnClickListener { checkButtonPress(game, third_result.text.toString()) }
    }

    fun startGame(game : Logic) : Logic{
        // Running this method will get all required files for UI
        game.newOperation()
        game.mixResults()
        return game
    }

    fun widgetsAssignment(game : Logic){
        // Assign values to widgets

        first_operator.text = game.currentOperation[0].toString()
        second_operator.text = game.currentOperation[1].toString()

        symbol.text = game.currentSymbol
        first_result.text = game.randomResults[0].toString()
        second_result.text = game.randomResults[1].toString()
        third_result.text = game.randomResults[2].toString()
    }


    fun playSound(status : String){
        // Play a sound acoording to his state

        var success : MediaPlayer? = MediaPlayer.create(this, R.raw.acierto)
        var error : MediaPlayer? = MediaPlayer.create(this, R.raw.error)
        var media : Unit ?

        media = when(status){
            "success" -> success?.start()
            else -> {
                error?.start() }
        }
    }

    fun showToast(){

        // A toast to show deleted items on display
        val toast = Toast.makeText(
            this,
            "Error :(, try again ", Toast.LENGTH_LONG
        )
        toast.show()
    }

    fun checkButtonPress(game : Logic, user : String){
        // This method will check if the pressed button contains the result
        if (user.toInt() == game.currentResult){
            // Correct
            // Restarting app and widgets
            playSound("success")
            startGame(game)
            widgetsAssignment(game)
        } else {
            playSound("error")
            showToast()
        }

    }

}