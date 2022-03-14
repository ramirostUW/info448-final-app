package edu.washington.info448_final_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import edu.washington.info448_final_app.repository.Comment


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var getUsersEmailId = "ss" //intent.getStringExtra("The users id")


        var getUsersEmail = "ss" //intent.getStringExtra("The users email")

        Log.i("MAIN_ACTIVITY", getUsersEmailId.toString())

        Log.i("MAIN_ACTIVITY", getUsersEmail.toString())

        val repo = (this.application as FinalAppApplication).repository

        val testVal = repo.getComments("INFO 448","ramirost@live.com")

        Log.i("MAIN_ACTIVITY", "" + testVal)

    }
}
