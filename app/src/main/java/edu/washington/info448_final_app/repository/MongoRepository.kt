package edu.washington.info448_final_app.repository
import android.util.Log
import com.mongodb.client.MongoDatabase
import org.litote.kmongo.*
import org.litote.kmongo.KMongo.createClient

object MongoRepository : ReviewRepository {
//This Class does NOT work, for one of two possible reasons; The kotlin Mongo driver is faulty, or I'm using it wrong
//Use APIRepository instead

    private fun initializeMongo(): MongoDatabase {
        val connectionString = "mongodb+srv://info448finalapp:info448finalapp@cluster0.x7en1.mongodb.net/finalApp?retryWrites=true&w=majority"
        val client = createClient(connectionString)
        return client.getDatabase("finalApp");
    }

    private val mongo = initializeMongo()

    override fun getReviews(courseID: String): List<CourseReview> {
        val mongoCol =  mongo.getCollection<CourseReview>("reviews")
        return mongoCol.find().asIterable().map { it }
    }

    override fun getCourses(): List<Course> {
        val mongoCol =  mongo.getCollection<Course>("classes")
        return mongoCol.find().asIterable().map { it }
    }

    override fun postReview(valToUpload: CourseReview) {
        val mongoCol =  mongo.getCollection<CourseReview>("reviews")
        mongoCol.insertOne(valToUpload)

    }

    override fun outputToLog() {
        Log.i("FinalApp", "Inside MongoRepository object")
    }
}