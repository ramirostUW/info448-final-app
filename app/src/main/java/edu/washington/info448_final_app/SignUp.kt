package edu.washington.info448_final_app

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
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

class SignUp : AppCompatActivity() {
    private var usersEmail= "";
    private var usersPassword = "";
    private var MAX = 150;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)




        var sign_up: Button = findViewById<Button>(R.id.sign_up)
        var email: EditText = findViewById<EditText>(R.id.EmailAddress)
        var password: EditText = findViewById<EditText>(R.id.Password)
        sign_up.setBackgroundColor(Color.RED)

        sign_up.setTextColor(Color.YELLOW)

        sign_up.setOnClickListener{
            try{
            when{
                TextUtils.isEmpty(email.text.toString().trim {it <= ' '}) -> {
                  var toast =  Toast.makeText(this, "Please enter a valid email", Toast.LENGTH_LONG);
                    toast.show()

                }

                TextUtils.isEmpty(password.text.toString().trim {it <= ' '}) -> {
                    var toast =  Toast.makeText(this, "Please enter a valid password", Toast.LENGTH_LONG);
                    toast.show()
                    System.arraycopy(password.length(),email.length(),1,1,1)

                }
                else ->{
                    var actualEmail = StringBuilder(150)
                    var actualPassWord = java.lang.StringBuilder(150)
                    this.usersEmail = email.text.toString()
                    actualEmail.append(this.usersEmail)
                    this.usersPassword = password.text.toString()
                    actualPassWord.append(this.usersPassword)

                    if(actualEmail.toString().length > MAX || actualPassWord.toString().length > MAX){
                        var toast =  Toast.makeText(this, "Please enter a valid password", Toast.LENGTH_LONG);
                        toast.show()

                    }
                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(this.usersEmail, this.usersPassword).addOnCompleteListener { FireBaseuser ->
                        run {
                            if (FireBaseuser.isSuccessful) {
                                var User: FirebaseUser = FireBaseuser.result!!.user!!

                                var createUser: StringBuffer = StringBuffer(this.MAX)

                                createUser.append(User.toString())

                                var toast =  Toast.makeText(this, "Login Succesful", Toast.LENGTH_LONG);
                                toast.show()
                                var intent = Intent(this, Login_Page::class.java)
                                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK


                                Log.i("SignUp", User.uid)

                                Log.i("SignUp", User.email as String)


                                intent.putExtra("The users id ", User.uid)

                                intent.putExtra("The users email", User.email)

                                startActivity(intent)
                                //finish()
                            }else{
                                Toast.makeText(this, FireBaseuser.exception!!.message.toString(), Toast.LENGTH_LONG).show()
                            }
                        }
                    }
                }
            }

        }catch(e: FirebaseException){
            Toast.makeText(this, "An erorr has occured please try again later", Toast.LENGTH_LONG).show()

            }
        }
    }
}