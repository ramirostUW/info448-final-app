package edu.washington.info448_final_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import edu.washington.info448_final_app.repository.CourseReview
import org.w3c.dom.Text

class ClassReview : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_class_review)

        //reference
        val intent = getIntent()
        val app = this.application as FinalAppApplication
        val classCode = intent.getStringExtra("CLASS_CODE")!!
        val className = intent.getStringExtra("CLASS_NAME")!!
        val classDescription = intent.getStringExtra("CLASS_DESCRIPTION")!!
        val classPreReqs = intent.getStringExtra("CLASS_PREREQS")!!
        val classCodeAndNameText = findViewById<TextView>(R.id.classCodeAndName)
        val classDescriptionText = findViewById<TextView>(R.id.classDescription)
        val classPrereqsText = findViewById<TextView>(R.id.classPreReqs)
        val btnPostReviewPage = findViewById<Button>(R.id.btnPostReviewPage)
        val reviewScrollView = findViewById<LinearLayout>(R.id.reviewScrollViewLayout)
        val reviews = app.repository.getReviews(classCode)

        //setText
        classCodeAndNameText.text = classCode + " | " + className
        classDescriptionText.text = "Description: " + classDescription
        classPrereqsText.text = "Prerequisites: " + classPreReqs

        //populating reviews
        if(reviews.size ==0) {
            val noReview = TextView(this)
            noReview.text = "No Reviews"
            reviewScrollView.addView(noReview)
        }else {
            for (review in reviews) {
                val newBtn = Button(this)
                newBtn.text =
                    review.numStars.toString() + " / 5 : " + review.description + " - " + review.author

                newBtn.setOnClickListener {
                    val intent = Intent(this, Comment::class.java)
                    intent.putExtra("Rating", review.numStars)
                    intent.putExtra("Review", review.description)
                    intent.putExtra("Author", review.author)
                    startActivity(intent)
                }

                reviewScrollView.addView(newBtn)
            }
        }

        btnPostReviewPage.setOnClickListener {
            val nextIntent = Intent(this, PostReview::class.java)
            nextIntent.putExtra("CLASS_CODE", classCode)
            startActivity(nextIntent)
        }
    }
}