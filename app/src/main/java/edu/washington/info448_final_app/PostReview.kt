package edu.washington.info448_final_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import edu.washington.info448_final_app.repository.Course
import edu.washington.info448_final_app.repository.CourseReview

class PostReview : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_review)

        //reference
        val app = this.application as FinalAppApplication
        val intent = getIntent()
        val rateClassCode = findViewById<TextView>(R.id.rateClassCode)
        val numStars = findViewById<EditText>(R.id.numStars)
        val description = findViewById<EditText>(R.id.description)
        val btnPostReview = findViewById<Button>(R.id.btnPostReview)
        val userEmail = app.getCurrentUserEmail()
        val classCode = intent.getStringExtra("CLASS_CODE")!!

        //setText
        rateClassCode.text = "Rate " + classCode

        //TODO: check errors (eg. star rating > 5 && star rating < 5)
        btnPostReview.setOnClickListener {
            val numStarsInput = Integer.parseInt(numStars.text.toString())
            val descriptionInput = description.text.toString()
            app.repository.postReview(CourseReview(classCode, userEmail, numStarsInput, descriptionInput))
            Toast.makeText(this, "Review Posted!", Toast.LENGTH_LONG).show()

        }
    }
}