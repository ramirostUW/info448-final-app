package edu.washington.info448_final_app.repository

import android.os.Build
import android.os.StrictMode
import android.util.Log
import com.google.gson.Gson
import java.net.URL

object APIRepository: ReviewRepository {
    init {

        if (Build.VERSION.SDK_INT > 9) {
            val gfgPolicy = StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(gfgPolicy)
        }
    }
    private val gson = Gson()
    private val apiURL = "https://boiling-ocean-79185.herokuapp.com"

    override fun getReviews(courseID: String): List<CourseReview> {
        val retrieveURL = apiURL +
                "/allReviewsByCourse?course=" + java.net.URLEncoder.encode(courseID, "utf-8")

        val jsonString = URL(retrieveURL).readText()
        val returnVal = gson.fromJson(jsonString, Array<CourseReview>::class.java).toList()

        return returnVal;

    }

    override fun getCourses(): List<Course> {

        val jsonString = URL(apiURL + "/allClasses").readText()
        val returnVal = gson.fromJson(jsonString, Array<Course>::class.java).toList()

        return returnVal;
    }

    override fun postReview(valToUpload: CourseReview){
        //https://boiling-ocean-79185.herokuapp.com/uploadReview?course=course&author=author&numStars=numStars&description=description
        val course = valToUpload.course;
        val author = valToUpload.author;
        val numStars = valToUpload.numStars;
        val description = valToUpload.description;

        val uploadAPIURL = apiURL +
                "/uploadReview?course=" + java.net.URLEncoder.encode(course, "utf-8") +
                "&author=" +  java.net.URLEncoder.encode(author, "utf-8")  +
                "&numStars=" + java.net.URLEncoder.encode("" + numStars, "utf-8") +
                "&description=" + java.net.URLEncoder.encode(description, "utf-8")
        val jsonString = URL(uploadAPIURL).readText()
    }

    override fun outputToLog(){
        Log.i("FinalApp", "Inside APIRepository object")
    }
}