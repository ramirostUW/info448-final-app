package edu.washington.info448_final_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.LinearLayout

class Classes : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_classes)

        val app = this.application as FinalAppApplication
        val courses = app.repository.getCourses()

        for(course in courses){
            val newbtn = Button(this);
            newbtn.text = course.classCode
            val scrollView = findViewById<LinearLayout>(R.id.scrollViewLayout)
            scrollView.addView(newbtn)
        }


        Log.i("HERE", courses[0].classCode)
        Log.i("HERE", courses[0].className)
        Log.i("HERE", courses[0].description)
        Log.i("HERE", courses[0].prereqs)


    }
}