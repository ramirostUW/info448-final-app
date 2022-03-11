package edu.washington.info448_final_app

import android.content.Intent
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

            //button listener
            newbtn.setOnClickListener {
                val nextIntent = Intent(this, ClassReview::class.java)
                nextIntent.putExtra("CLASS_CODE", course.classCode)
                nextIntent.putExtra("CLASS_NAME", course.className)
                nextIntent.putExtra("CLASS_DESCRIPTION", course.description)
                nextIntent.putExtra("CLASS_PREREQS", course.prereqs)
                startActivity(nextIntent)
            }
            scrollView.addView(newbtn)
        }


        Log.i("HERE", courses[0].classCode)
        Log.i("HERE", courses[0].className)
        Log.i("HERE", courses[0].description)
        Log.i("HERE", courses[0].prereqs)


    }
}
