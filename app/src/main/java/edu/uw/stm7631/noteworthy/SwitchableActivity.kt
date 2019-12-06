package edu.uw.stm7631.noteworthy

import android.R.attr.fragment
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_courses.*
import java.util.*
import kotlin.collections.ArrayList
import edu.uw.stm7631.noteworthy.ui.notes.NotesFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_notes.*


class SwitchableActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view) //sets the bottom navigation view from xml file.

        val intent = getIntent()
        if (intent.hasExtra("Photo note")) {      //checks if extras are set or not.
            val sessionId = intent.getStringExtra("Photo note") //checks if extra string is set or not.
            Toast.makeText(this, sessionId, Toast.LENGTH_LONG).show()
//            var fragment = NotesFragment.newInstance(sessionId!!)
//            supportFragmentManager.beginTransaction().run {
//                add(R.id.nav_host_fragment, fragment)
//                addToBackStack(null)
//                commit()
//            }
//            navView.selectedItemId = (R.id.navigation_notes)

        }

        val navController = findNavController(R.id.nav_host_fragment) //manages the navigation for navhost.
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
        val user = db.collection("users").document(CourseContent.auth.currentUser?.email!!)
        user.get()
            .addOnSuccessListener { user ->
                CourseContent.MYCOURSES.clear()
                val courses = user.data?.getValue("classes") as ArrayList<DocumentReference>
                for (cor in courses) {
                    cor.get().addOnSuccessListener {
                        CourseContent.MYCOURSES.add(CourseContent.CourseItem(it["code"].toString(), it["name"].toString(), it["date"].toString()))
                        course_recycle.adapter = RecyclerViewAdapter(CourseContent.MYCOURSES, this)
                        course_recycle.layoutManager = LinearLayoutManager(this)  //sets linear layout manager to recycler view.
                        if (CourseContent.MYCOURSES.isEmpty()) {
                            Toast.makeText(this, "Add some courses!", Toast.LENGTH_LONG).show() // shows toast message to add courses.
                        }
                    }
                }
            }
            .addOnFailureListener { exception ->
                CourseContent.ITEMS.add(CourseContent.CourseItem("failed", "hi", "hi"))
            }

    }

}