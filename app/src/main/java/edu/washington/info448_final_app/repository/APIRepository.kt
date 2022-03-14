package edu.washington.info448_final_app.repository

import android.os.Build
import android.os.StrictMode
import android.util.Log
import android.widget.Toast
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

    override fun getComments(courseID: String, author: String): List<Comment> {
        val retrieveURL = apiURL +
                "/allComentsForReview?course=" + java.net.URLEncoder.encode(courseID, "utf-8") +
                "&author=" +  java.net.URLEncoder.encode(author, "utf-8")
        Log.i("MAIN_ACTIVITY", retrieveURL)
        val jsonString = URL(retrieveURL).readText()
        val returnVal = gson.fromJson(jsonString, Array<Comment>::class.java).toList()

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

    override fun postComment(valToUpload: Comment){
        //https://boiling-ocean-79185.herokuapp.com/uploadReview?course=course&author=author&numStars=numStars&description=description
        val reviewCourse = valToUpload.reviewCourse;
        val reviewAuthor = valToUpload.reviewAuthor;
        val commentAuthor = valToUpload.commentAuthor;
        val comment = valToUpload.comment;

        val uploadAPIURL = apiURL +
                "/makeComment?reviewCourse=" + java.net.URLEncoder.encode(reviewCourse, "utf-8") +
                "&reviewAuthor=" +  java.net.URLEncoder.encode(reviewAuthor, "utf-8")  +
                "&commentAuthor=" + java.net.URLEncoder.encode("" + commentAuthor, "utf-8") +
                "&comment=" + java.net.URLEncoder.encode(comment, "utf-8")
        val jsonString = URL(uploadAPIURL).readText()

    }

    override fun outputToLog(){
        Log.i("FinalApp", "Inside APIRepository object")
    }

    override fun isStudent(email: String): Boolean{
        //http://127.0.0.1:6440/isStudent?user=ramirost%40live.com

        val retrievalURL = apiURL +
                "/isStudent?user=" + java.net.URLEncoder.encode(email, "utf-8")

        val jsonString = URL(retrievalURL).readText()
        val returnVal = gson.fromJson(jsonString, Array<Boolean>::class.java)[0]

        return returnVal;
    }

    override fun setStudentStatus(email: String, status: String){
        val retrievalURL = apiURL +
                "/registerUserStatus?user=" + java.net.URLEncoder.encode(email, "utf-8") +
                "&role=" + java.net.URLEncoder.encode(status, "utf-8")

        Log.i("MAIN_ACTIVITY", retrievalURL)
        val jsonString = URL(retrievalURL).readText()
    }
}