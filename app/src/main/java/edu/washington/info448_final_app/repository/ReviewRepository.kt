package edu.washington.info448_final_app.repository

interface ReviewRepository {
    fun getReviews(courseID: String) : List<CourseReview>;

    fun getCourses(): List<Course>;

    fun postReview(valToUpload: CourseReview);

    fun postComment(valToUpload: Comment);

    fun getComments(courseID: String, author: String): List<Comment>

    fun outputToLog();
}