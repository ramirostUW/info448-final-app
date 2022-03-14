package edu.washington.info448_final_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import edu.washington.info448_final_app.repository.Course


class Classes : AppCompatActivity() {

    private lateinit var scrollView: LinearLayout
    private lateinit var spinner: Spinner
    private lateinit var app: FinalAppApplication
    private var order = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_classes)

        app = this.application as FinalAppApplication

        scrollView = findViewById<LinearLayout>(R.id.scrollViewLayout)
        spinner = findViewById(R.id.spnOrder)
        ArrayAdapter.createFromResource(
            this,
            R.array.sort_order,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
        }

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                if (p0 != null) {
                    order = p0.getItemAtPosition(p2).toString()
                    val courses = getsortedCourses(order)
                    removeCourses()
                    addCourses(courses)
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

        }

//        Log.i("HERE", courses[0].classCode)
//        Log.i("HERE", courses[0].className)
//        Log.i("HERE", courses[0].description)
//        Log.i("HERE", courses[0].prereqs)


    }

    fun getsortedCourses(order: String) : List<Course> {
        var courses = app.repository.getCourses()
        when(order){
            "Rating" -> {
                Log.i("debug", "order is $order")
                courses = courses.sortedBy { it.avgStars*-1 }
            }
        }
        return courses
    }

    fun addCourses(courses: List<Course>) {
        for(course in courses){
            val newbtn = Button(this);
            newbtn.text = course.classCode

            var prerequisites = course.prereqs
            if(prerequisites == null) {
                prerequisites = "no prerequisite"
            }

            //button listener
            newbtn.setOnClickListener {
                val nextIntent = Intent(this, ClassReview::class.java)
                nextIntent.putExtra("CLASS_CODE", course.classCode)
                nextIntent.putExtra("CLASS_NAME", course.className)
                nextIntent.putExtra("CLASS_DESCRIPTION", course.description)
                nextIntent.putExtra("CLASS_PREREQS", prerequisites)
                startActivity(nextIntent)
            }
            scrollView.addView(newbtn)
        }
    }

    fun removeCourses() {
        if(scrollView.childCount != 0) {
            scrollView.removeAllViews()
        }
    }

}
