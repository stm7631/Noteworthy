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
                            Toast.makeText(this, getResources().getString(R.string.up_success), Toast.LENGTH_SHORT).show()
                            auth.signInWithEmailAndPassword(newemail.text.toString(), newpassword.text.toString())
                            val intent = Intent(this, SwitchableActivity::class.java)
                            startActivity(intent)
                        } else {
                            //Registration error
                            Toast.makeText(this, getResources().getString(R.string.up_fail), Toast.LENGTH_LONG).show()
                        }
                    }
            }
        }
        login.setOnClickListener {
            setContentView(R.layout.signin)
            signinButton.setOnClickListener {
                auth.signInWithEmailAndPassword(email.text.toString(), password.text.toString()).addOnCompleteListener {
                    if (it.isSuccessful) {
                        Toast.makeText(this, getResources().getString(R.string.in_success), Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, SwitchableActivity::class.java)
                        startActivity(intent)
                    } else {
                        //Registration error
                        Toast.makeText(this, getResources().getString(R.string.in_fail), Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }
}






