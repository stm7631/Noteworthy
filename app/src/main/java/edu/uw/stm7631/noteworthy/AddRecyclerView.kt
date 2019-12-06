package edu.uw.stm7631.noteworthy

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import edu.uw.stm7631.noteworthy.CourseContent.MYCOURSES
import edu.uw.stm7631.noteworthy.CourseContent.auth
import edu.uw.stm7631.noteworthy.ui.courses.CoursesAddFragment
import edu.uw.stm7631.noteworthy.ui.courses.CoursesFragment
import kotlinx.android.synthetic.main.course_add_card.view.*
import kotlinx.android.synthetic.main.fragment_courses.*

class AddRecyclerViewAdapter(private val values: List<CourseContent.CourseItem>, private val context: Context)
    : RecyclerView.Adapter<AddRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.course_add_card, parent, false)
        view.add_button.setOnClickListener {
            var db = FirebaseFirestore.getInstance()
            val docRef = db.collection("classes")
                .document(view.course_name.text.toString().replace("\\s".toRegex(), ""))
            val users = db.collection("users").whereEqualTo("email", auth.currentUser?.email)

            users.get()
                .addOnSuccessListener { users ->
                    for (user in users) {
                        db.collection("users").document(user.id).update("classes", FieldValue.arrayUnion(docRef))
                            .addOnSuccessListener {
                                Toast.makeText(view.context, R.string.success, Toast.LENGTH_SHORT).show()
                                docRef.get().addOnSuccessListener {
                                    MYCOURSES.add(CourseContent.CourseItem(it.getString("code")!!, it.getString("name")!!, it.getString("date")!!))
                                }
                            }
                            .addOnFailureListener {
                                Toast.makeText(view.context, R.string.failed, Toast.LENGTH_SHORT).show()
                            }
                    }
                }
                .addOnFailureListener { exception ->
                    Toast.makeText(view.context, R.string.failed, Toast.LENGTH_LONG).show()
                }



            view.add_button.setImageResource(R.drawable.add_course_selected)
        }

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.course.text = item.course
        holder.instructor.text = item.instructor
        holder.date.text = item.date

        with(holder.itemView) {
            tag = item
        }
    }

    override fun getItemCount() = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val course: TextView = view.course_name
        val instructor: TextView = view.course_instructor
        val date: TextView = view.course_date
    }
}