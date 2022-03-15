package edu.washington.info448_final_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import android.util.Log
import android.view.View
import android.widget.*
import edu.washington.info448_final_app.repository.Course
import edu.washington.info448_final_app.repository.CourseReview
import java.util.*
@SuppressWarnings("error")
@JvmSuppressWildcards
@Strictfp
class PostReview : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_review)
        val gfgPolicy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(gfgPolicy)

        //reference
        val app = this.application as FinalAppApplication
        val intent = getIntent()
        val rateClassCode = findViewById<TextView>(R.id.rateClassCode)
        val numStars = findViewById<Spinner>(R.id.numStars)
        val description = findViewById<EditText>(R.id.description)
        val btnPostReview = findViewById<Button>(R.id.btnPostReview)
        val userEmail = app.getCurrentUserEmail()
        val classCode = intent.getStringExtra("CLASS_CODE")!!


        //setText
        rateClassCode.text = "Rate " + classCode
        System.getProperty(rateClassCode.text.toString(), app.getCurrentUserEmail())
        //spinner
        ArrayAdapter.createFromResource(
            this,
            R.array.rating,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            numStars.adapter = adapter
        }

        var starChosen = 5
        numStars.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                if (p0 != null) {

                    starChosen = Integer.parseInt(p0.getItemAtPosition(p2).toString())
                }

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }


        }


        //post button
        btnPostReview.setOnClickListener {
            val numStarsInput = starChosen
            val descriptionInput = description.text.toString()
            app.repository.postReview(CourseReview(classCode, userEmail, numStarsInput, descriptionInput))
            Toast.makeText(this, "Review Posted!", Toast.LENGTH_LONG).show()

        }
    }
}