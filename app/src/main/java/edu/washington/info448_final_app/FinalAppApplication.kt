package edu.washington.info448_final_app

import android.app.Application
import android.util.Log
import edu.washington.info448_final_app.repository.APIRepository
import edu.washington.info448_final_app.repository.ReviewRepository

class FinalAppApplication: Application() {

    val repository: ReviewRepository = APIRepository
    public override fun onCreate() {
        super.onCreate()
        Log.i("FinalApp", "OnStart event fired")

        repository.outputToLog()
    }
}