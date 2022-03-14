package edu.washington.info448_final_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class Comment : AppCompatActivity() {

    private lateinit var reviewRating: TextView
    private lateinit var reviewAuthor: TextView
    private lateinit var reviewText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comment)

        val rating = this.intent.getIntExtra("Rating", 0)
        val author = this.intent.getStringExtra("Author")
        val description = this.intent.getStringExtra("Review")

        reviewRating = findViewById(R.id.comment_rating)
        reviewAuthor = findViewById(R.id.comment_author)
        reviewText = findViewById(R.id.comment_review)

        reviewRating.text = getString(R.string.comment_rating_label, rating.toString())
        reviewAuthor.text = getString(R.string.comment_author_label, author)
        reviewText.text = description
    }
}