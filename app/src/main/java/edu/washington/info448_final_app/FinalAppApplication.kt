package edu.washington.info448_final_app

import android.app.Application
import edu.washington.info448_final_app.repository.APIRepository
import edu.washington.info448_final_app.repository.ReviewRepository
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

class FinalAppApplication: Application() {

    val repository: ReviewRepository = APIRepository

    public override fun onCreate() {
        super.onCreate()
        Log.i("FinalApp", "OnStart event fired")

        repository.outputToLog()
    }

    public fun isSignedIn() : Boolean {
        return (FirebaseAuth.getInstance().getCurrentUser() != null)
    }
    public fun getCurrentUID () : String{
        if(isSignedIn())
        {
            return FirebaseAuth.getInstance().uid as String
        }

        else{
            return "Invalid User";
        }
    }
    public fun getCurrentUserEmail () : String{
        if(isSignedIn())
        {
            return FirebaseAuth.getInstance().currentUser?.email as String
        }

        else{
            return "Invalid User";
        }
    }

    public fun checkIfStudent (): Boolean {
        return repository.isStudent(getCurrentUserEmail())
    }
}