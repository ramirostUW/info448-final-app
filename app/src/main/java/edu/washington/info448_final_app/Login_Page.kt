package edu.washington.info448_final_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.core.view.isVisible
import com.google.firebase.auth.FirebaseAuth


class Login_Page : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_page)

        val applicationObj = this.application as FinalAppApplication

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

    override fun onStart() {
        super.onStart()

        val applicationObj = this.application as FinalAppApplication
        var signIn : Button = findViewById<Button>(R.id.sign_in)

        var signUp: Button = findViewById<Button>(R.id.sign_up)

        Log.i("MAIN_ACTIVITY", "" + applicationObj.isSignedIn())
        Log.i("MAIN_ACTIVITY", "currentUserID: " + applicationObj.getCurrentUID())
        Log.i("MAIN_ACTIVITY", "currentUserID: " + applicationObj.getCurrentUserEmail())

        if(applicationObj.isSignedIn()){
            signIn.setText("Browse Classes");
            signIn.setOnClickListener {
                val nextIntent = Intent(this, Classes::class.java)
                startActivity(nextIntent)
            }

            signUp.setText("Log Out")

            signUp.setOnClickListener {
                FirebaseAuth.getInstance().signOut()
                var intent = Intent(this, Login_Page::class.java)
                startActivity(intent)
            }

        }

    }
}
