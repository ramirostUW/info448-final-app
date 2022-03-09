package edu.washington.info448_final_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.TextView
import edu.washington.info448_final_app.repository.CourseReview
import org.w3c.dom.Text

class ClassReview : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_class_review)

        //reference
        val intent = getIntent()
        val classCode = intent.getStringExtra("CLASS_CODE")!!
        val className = intent.getStringExtra("CLASS_NAME")!!
        val classDescription = intent.getStringExtra("CLASS_DESCRIPTION")!!
        val classPreReqs = intent.getStringExtra("CLASS_PREREQS")!!
        val classCodeAndNameText = findViewById<TextView>(R.id.classCodeAndName)
        val classDescriptionText = findViewById<TextView>(R.id.classDescription)
        val classPrereqsText = findViewById<TextView>(R.id.classPreReqs)
        val btnPostReviewPage = findViewById<Button>(R.id.btnPostReviewPage)


        //setText
        classCodeAndNameText.text = classCode + " | " + className
        classDescriptionText.text = "Description: " + classDescription
        classPrereqsText.text = "Prerequisites: " + classPreReqs

        //TODO: need to work on populating reviews

        btnPostReviewPage.setOnClickListener {
            val nextIntent = Intent(this, PostReview::class.java)
            nextIntent.putExtra("CLASS_CODE", classCode)
            startActivity(nextIntent)
        }
    }
}