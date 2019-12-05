package edu.uw.stm7631.noteworthy

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
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

class SwitchableActivity : AppCompatActivity() {
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
        val user = db.collection("users").document(CourseContent.auth.currentUser?.email!!)
        user.get()
            .addOnSuccessListener { user ->
                val courses = user.data?.getValue("classes") as ArrayList<DocumentReference>
                for (cor in courses) {
                    cor.get().addOnSuccessListener {
                        CourseContent.MYCOURSES.add(CourseContent.CourseItem(it["code"].toString(), it["name"].toString(), it["date"].toString()))
                        course_recycle.adapter = RecyclerViewAdapter(CourseContent.MYCOURSES, this)
                        course_recycle.layoutManager = LinearLayoutManager(this)
                    }
                }
            }
            .addOnFailureListener { exception ->
                CourseContent.ITEMS.add(CourseContent.CourseItem("failed", "hi", "hi"))
            }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu to use in the action bar
        val inflater = menuInflater
        inflater.inflate(R.menu.example_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.undo -> {
                item.title = "Undo"
                true
            }
            R.id.redo -> {
                item.title = "Redo"
                true
            }
            R.id.find -> {
                item.title = "Find"
                true
            }
            R.id.settings -> {
                item.title = "Settings"


            }
            R.id.share -> {
                item.title = "Share"
            }}
        return super.onOptionsItemSelected(item)
    }
}