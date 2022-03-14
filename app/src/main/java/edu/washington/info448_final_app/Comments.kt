package edu.washington.info448_final_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import edu.washington.info448_final_app.repository.CourseReview
import org.w3c.dom.Text

class Comments : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comments)

        //reference
        val intent = getIntent()
        val app = this.application as FinalAppApplication
        val classCode = intent.getStringExtra("CLASS_CODE")!!
        //"INFO 448"
        val author = intent.getStringExtra("author")!!
            //"ramirost@live.com"
        val reviewDescription = intent.getStringExtra("REVIEW_DESCRIPTION")!!
            //"Application development for Android devices. Covers implementation of ...\"\n"
        val classCodeAndNameText = findViewById<TextView>(R.id.classCodeAndName)
        val classDescriptionText = findViewById<TextView>(R.id.classDescription)
        val btnPostReviewPage = findViewById<Button>(R.id.btnPostReviewPage)
        val reviewScrollView = findViewById<LinearLayout>(R.id.reviewScrollViewLayout)
        val reviews = app.repository.getReviews(classCode)
        val comments = app.repository.getComments(classCode, author)

        //setText
        classCodeAndNameText.text = "Review of " + classCode + " by " + author//classCode + " | " + className
        classDescriptionText.text = "Description: " + reviewDescription

        //populating reviews
        if(reviews.size ==0) {
            val noReview = TextView(this)
            noReview.text = "No Comments"
            reviewScrollView.addView(noReview)
        }else {
            for (comment in comments) {
                val newBtn = Button(this)
                newBtn.text =
                    comment.commentAuthor + " says : " + comment.comment
                reviewScrollView.addView(newBtn)
            }
        }

        btnPostReviewPage.setOnClickListener {
            val nextIntent = Intent(this, PostComment::class.java)
            nextIntent.putExtra("CLASS_CODE", classCode)
            nextIntent.putExtra("REVIEW_AUTHOR", author)
            nextIntent.putExtra("REVIEW_DESCRIPTION", reviewDescription)
            startActivity(nextIntent)
        }
    }
}