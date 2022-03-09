package edu.washington.info448_final_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class Classes : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_classes)

        val app = this.application as FinalAppApplication
        val courses = app.repository.getCourses()

        Log.i("HERE", courses[0].classCode)
        Log.i("HERE", courses[0].className)
        Log.i("HERE", courses[0].description)
        Log.i("HERE", courses[0].prereqs)


    }
}