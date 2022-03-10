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
            signIn.isVisible = false;
            signUp.setText("Log Out")

            signUp.setOnClickListener {
                FirebaseAuth.getInstance().signOut()
                var intent = Intent(this, Login_Page::class.java)
                startActivity(intent)
            }

            // to be modified
            val nextIntent = Intent(this, ClassReview::class.java)
            val courses = applicationObj.repository.getCourses()
            nextIntent.putExtra("CLASS_CODE", courses[0].classCode)
            nextIntent.putExtra("CLASS_NAME", courses[0].className)
            nextIntent.putExtra("CLASS_DESCRIPTION", courses[0].description)
            nextIntent.putExtra("CLASS_PREREQS", courses[0].prereqs)

            startActivity(nextIntent)

        }

    }
}