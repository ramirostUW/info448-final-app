package edu.washington.info448_final_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.FirebaseAuthRegistrar
import com.google.firebase.auth.ActionCodeInfo
import com.google.firebase.auth.FirebaseAuthProvider
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.FirebaseAppLifecycleListener
import com.google.firebase.FirebaseError
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.ktx.Firebase
import com.google.android.gms.tasks.OnCompleteListener


class Sign_In_Page : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in_page)


        var emailInput = findViewById<EditText>(R.id.EmailAddress)

        var password = findViewById<EditText>(R.id.Password)


        val btn = findViewById<Button>(R.id.sign_in)

        btn.setOnClickListener {
            when {
                TextUtils.isEmpty(emailInput.text.toString().trim { it <= ' ' }) -> {
                    var toast =
                        Toast.makeText(this, "Please enter a valid email", Toast.LENGTH_LONG);
                    toast.show()

                }

                TextUtils.isEmpty(password.text.toString().trim { it <= ' ' }) -> {
                    var toast =
                        Toast.makeText(this, "Please enter a valid password", Toast.LENGTH_LONG);
                    toast.show()
                    System.arraycopy(password.length(), emailInput.length(), 1, 1, 1)

                }

                (FirebaseAuth.getInstance().getCurrentUser() != null)->{
                    var toast =
                        Toast.makeText(this, "You are already logged in!", Toast.LENGTH_LONG);
                    Log.i("Sign_In", "User is already logged in")
                    toast.show()
                    var intent = Intent(this, Login_Page::class.java)
                    startActivity(intent)
                }

                else -> {
                    var toast =
                        Toast.makeText(this, "Trying to sign up!", Toast.LENGTH_LONG);
                    toast.show()

                    val enteredEmail = emailInput.text.toString();
                    val enteredPass = password.text.toString();
                    val auth = FirebaseAuth.getInstance()
                    auth.signInWithEmailAndPassword(enteredEmail, enteredPass)
                        .addOnCompleteListener(this) { task ->
                            if (task.isSuccessful) {
                                var intent = Intent(this, Login_Page::class.java)
                                startActivity(intent)
                            } else {
                                Toast.makeText(this, "Failed to log in",
                                    Toast.LENGTH_SHORT).show()
                            }
                        }
                }
            }
        }


    }
}