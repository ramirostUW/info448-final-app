package edu.washington.info448_final_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import edu.washington.info448_final_app.repository.Comment
import edu.washington.info448_final_app.repository.Course
import edu.washington.info448_final_app.repository.CourseReview

class PostComment : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_comment)

        //reference
        val app = this.application as FinalAppApplication
        val intent = getIntent()
        val rateClassCode = findViewById<TextView>(R.id.rateClassCode)
        val description = findViewById<EditText>(R.id.description)
        val btnPostComment = findViewById<Button>(R.id.btnPostComment)
        val userEmail = app.getCurrentUserEmail()
        val classCode = intent.getStringExtra("CLASS_CODE")!!
        val reviewAuthorLabel = findViewById<TextView>(R.id.post_comment_author)
        val reviewDescriptionLabel = findViewById<TextView>(R.id.post_comment_review)
        val reviewAuthor = intent.getStringExtra("REVIEW_AUTHOR")
        val reviewDescription = intent.getStringExtra("REVIEW_DESCRIPTION")

        //setText
        reviewAuthorLabel.text = "Author: $reviewAuthor"
        reviewDescriptionLabel.text = reviewDescription

        btnPostComment.setOnClickListener {
            val descriptionInput = description.text.toString()
            app.repository.postComment(Comment(classCode, reviewAuthor!!, userEmail, descriptionInput))
            Toast.makeText(this, "Review Posted!", Toast.LENGTH_LONG).show()
            finish()
        }
    }
}