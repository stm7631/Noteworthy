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
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import edu.uw.stm7631.noteworthy.CourseContent.ITEMS
import edu.uw.stm7631.noteworthy.CourseContent.auth
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_courses.*
import kotlinx.android.synthetic.main.signin.*
import kotlinx.android.synthetic.main.signup.*
import kotlinx.android.synthetic.main.welcome.*
import java.util.*



class MainActivity : AppCompatActivity() {
    public override fun onStart() {
        super.onStart()
        auth = FirebaseAuth.getInstance()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if (currentUser != null) {  //checks if the user is not null before starting activity.
            val intent = Intent(this, SwitchableActivity::class.java)
            startActivity(intent)
        }
    }


override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.welcome)
        var db = FirebaseFirestore.getInstance()
        signup.setOnClickListener {  // user clicks on Sign up button to initilaise SignUp process.
            setContentView(R.layout.signup)
            signupButton.setOnClickListener {
                auth.createUserWithEmailAndPassword(newemail.text.toString(), newpassword.text.toString())  // Clicking on SignUp, the email and password form view appears.
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            //Registration OK
                            db.collection("users").document(newemail.text.toString()).set(
                                hashMapOf(
                                    "name" to name.text.toString(),    //converts user name to string.
                                    "email" to newemail.text.toString(),  // converts email id to string.
                                    "classes" to ArrayList<DocumentReference>()  
                                ))
                                .addOnSuccessListener { //adds listener if registration is successful.
                                    Toast.makeText(this, "signup successful!", Toast.LENGTH_SHORT).show()  // Displays toast message when signup is successful.
                                    auth.signInWithEmailAndPassword(newemail.text.toString(), newpassword.text.toString())  //Converts email and password to string format.
                                    val intent = Intent(this, SwitchableActivity::class.java)
                                    startActivity(intent)
                                }
                                .addOnFailureListener {
                                    Toast.makeText(this, "signup failed :(", Toast.LENGTH_SHORT).show() // Adds listener if Signup fails. The resulting action is toast message.
                                }
                        } else {
                            //Registration error
                            Toast.makeText(this, getResources().getString(R.string.up_fail), Toast.LENGTH_LONG).show()
                        }
                    }
            }
        }
        login.setOnClickListener {          //When user clicks on Sign In
            setContentView(R.layout.signin)
            signinButton.setOnClickListener {
                auth.signInWithEmailAndPassword(email.text.toString(), password.text.toString()).addOnCompleteListener {// lets users sign in with email and password.
                    if (it.isSuccessful) {
                        Toast.makeText(this, getResources().getString(R.string.in_success), Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, SwitchableActivity::class.java)  //starts switchable activity if signin is successful.
                        startActivity(intent)
                    } else {
                        //Registration error
                        Toast.makeText(this, getResources().getString(R.string.in_fail), Toast.LENGTH_LONG).show() //toast message if signin fails.
                    }
                }
            }
        }
    }

}










