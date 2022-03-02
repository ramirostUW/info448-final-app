package edu.washington.info448_final_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var getUsersEmailId = intent.getStringExtra("The users id")

        var getUsersEmail = intent.getStringExtra("The users email")

        Log.i("MAIN_ACTIVITY", getUsersEmailId.toString())

        Log.i("MAIN_ACTIVITY", getUsersEmail.toString())

    }
}