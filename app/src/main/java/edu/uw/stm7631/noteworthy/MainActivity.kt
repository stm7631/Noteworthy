package edu.uw.stm7631.noteworthy

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import android.view.Menu
import java.util.Calendar
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_courses.*
import kotlinx.android.synthetic.main.signin.*
import kotlinx.android.synthetic.main.signup.*
import kotlinx.android.synthetic.main.welcome.*

private lateinit var auth: FirebaseAuth

class MainActivity : AppCompatActivity() {
    public override fun onStart() {
        super.onStart()
        auth = FirebaseAuth.getInstance()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if (currentUser != null) {
            val intent = Intent(this, SwitchableActivity::class.java)
            startActivity(intent)
        }
    }


override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.welcome)
        signup.setOnClickListener {
            setContentView(R.layout.signup)
            signupButton.setOnClickListener {
                auth.createUserWithEmailAndPassword(newemail.text.toString(), newpassword.text.toString())
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            //Registration OK
                            val firebaseUser = auth.currentUser
                            Toast.makeText(this, "signup successful!", Toast.LENGTH_SHORT).show()
                            auth.signInWithEmailAndPassword(newemail.text.toString(), newpassword.text.toString())
                            val intent = Intent(this, SwitchableActivity::class.java)
                            startActivity(intent)
                        } else {
                            //Registration error
                            Toast.makeText(this, "signup failed", Toast.LENGTH_SHORT).show()
                        }
                    }
            }
        }
        login.setOnClickListener {
            setContentView(R.layout.signin)
            signinButton.setOnClickListener {
                auth.signInWithEmailAndPassword(email.text.toString(), password.text.toString()).addOnCompleteListener {
                    if (it.isSuccessful) {
                        val intent = Intent(this, SwitchableActivity::class.java)
                        startActivity(intent)
                    } else {
                        //Registration error
                        Toast.makeText(this, "signin failed", Toast.LENGTH_SHORT)
                    }
                }
            }
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










