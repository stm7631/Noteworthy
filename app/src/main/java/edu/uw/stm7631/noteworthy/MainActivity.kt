package edu.uw.stm7631.noteworthy

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_courses.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_courses, R.id.navigation_notes, R.id.navigation_camera
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)


        var db = FirebaseFirestore.getInstance()

        val docRef = db.collection("users")
        docRef.get()
            .addOnSuccessListener { documents ->
                for (doc in documents) {
                    CourseContent.ITEMS.add(CourseContent.CourseItem(doc.data["first"].toString(), "hi", "hi"))
                }
                course_recycle.adapter = RecyclerViewAdapter(CourseContent.ITEMS)
                course_recycle.layoutManager = LinearLayoutManager(this)
            }
            .addOnFailureListener { exception ->
                CourseContent.ITEMS.add(CourseContent.CourseItem("failed", "hi", "hi"))
                course_recycle.adapter = RecyclerViewAdapter(CourseContent.ITEMS)
                course_recycle.layoutManager = LinearLayoutManager(this)
            }

    }
}
