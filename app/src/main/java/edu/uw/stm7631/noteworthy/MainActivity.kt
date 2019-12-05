package edu.uw.stm7631.noteworthy

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import android.view.Menu
import android.view.MenuItem
import java.text.SimpleDateFormat
import java.util.Date
import java.time.LocalDateTime
import java.util.Calendar
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

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
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu to use in the action bar
        val inflater = menuInflater
        inflater.inflate(R.menu.example_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    fun displayCurrentDateAndTime() {
        val now = LocalDateTime.now()
        var formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")







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






