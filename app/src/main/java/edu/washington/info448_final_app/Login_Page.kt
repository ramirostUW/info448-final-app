package edu.washington.info448_final_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Login_Page : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_page)


        var signIn : Button = findViewById<Button>(R.id.sign_in)

        var signUp: Button = findViewById<Button>(R.id.sign_up)


        signIn.setOnClickListener {
            val intent = Intent(this@Login_Page, Sign_In_Page::class.java)
            startActivity(intent)
        }



        signUp.setOnClickListener{
            val intent = Intent(this@Login_Page, SignUp::class.java)
            startActivity(intent)
        }
    }
}