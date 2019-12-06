package edu.uw.stm7631.noteworthy

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import edu.uw.stm7631.noteworthy.CourseContent.USER
import kotlinx.android.synthetic.main.fragment_courses.*
import kotlin.collections.ArrayList
import edu.uw.stm7631.noteworthy.ui.notes.NotesFragment

enum class NotificationID {
    BASIC,
}

class SwitchableActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view) //sets the bottom navigation view from xml file.

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Create the NotificationChannel
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val mChannel = NotificationChannel("BASIC", "Channel 1", importance)
            mChannel.description = "This is a test channel."
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(mChannel)
        }

        val intent = getIntent()
        if (intent.hasExtra("Photo note")) {      //checks if extras are set or not.
            val sessionId = intent.getStringExtra("Photo note") //checks if extra string is set or not.
            var fragment = NotesFragment.newInstance(sessionId!!)
            supportFragmentManager.beginTransaction().run {
                add(R.id.nav_host_fragment, fragment)
                addToBackStack(null)
                commit()
            }
            navView.selectedItemId = (R.id.navigation_notes)

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


        //setup database and populate with courses
        var db = FirebaseFirestore.getInstance()
        val user = db.collection("users").document(CourseContent.auth.currentUser?.email!!)
        user.get()
            .addOnSuccessListener { user ->
                USER = user["name"].toString()
                CourseContent.MYCOURSES.clear()
                val courses = user.data?.getValue("classes") as ArrayList<DocumentReference>
                for (cor in courses) { // For each course stores in your course list on Firebase retrieve it and add it to your course list
                    cor.get().addOnSuccessListener {
                        CourseContent.MYCOURSES.add(CourseContent.CourseItem(it["code"].toString(), it["name"].toString(), it["date"].toString()))
                        course_recycle.adapter = RecyclerViewAdapter(CourseContent.MYCOURSES, this)
                        course_recycle.layoutManager = LinearLayoutManager(this)  //sets linear layout manager to recycler view.
                        if (CourseContent.MYCOURSES.isEmpty()) {
                            Toast.makeText(this, R.string.empty, Toast.LENGTH_LONG).show() // shows toast message to add courses.
                        }
                    }
                }
                // Add listeners to let yourself know when people besides yourself add a note to a class in your list
                if (!courses.isEmpty()) {
                    db.collection("notes").whereIn("class", courses).whereLessThan("name", USER)
                        .addSnapshotListener { notes, firebaseFirestoreException ->
                            displayNotificationSimple()
                        }
                    db.collection("notes").whereIn("class", courses).whereGreaterThan("name", USER)
                        .addSnapshotListener { notes, firebaseFirestoreException ->
                            displayNotificationSimple()
                        }
                }
            }
            .addOnFailureListener { exception ->
                CourseContent.ITEMS.add(CourseContent.CourseItem("failed", "hi", "hi"))
            }
    }

    //setup basic notification for showing when new users notes are added
    fun displayNotificationSimple() {
        val builder = buildBasicNotification()
        showBasicNotification(builder)
    }

    //build notification
    private fun buildBasicNotification(): NotificationCompat.Builder {
        var builder = NotificationCompat.Builder(this, "BASIC")
            .setSmallIcon(android.R.drawable.ic_menu_add)
            .setContentTitle("A note was added to one of your registered courses!")
            .setAutoCancel(true)

        return builder
    }

    //show notification
    private fun showBasicNotification(builder: NotificationCompat.Builder) {
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(NotificationID.BASIC.ordinal, builder.build())
    }
}