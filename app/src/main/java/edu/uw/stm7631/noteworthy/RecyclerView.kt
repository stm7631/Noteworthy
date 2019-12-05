package edu.uw.stm7631.noteworthy

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import edu.uw.stm7631.noteworthy.ui.courses.CoursesFragment
import edu.uw.stm7631.noteworthy.ui.courses.NoteListFragment
import kotlinx.android.synthetic.main.course_card.view.*

class RecyclerViewAdapter(private val values: List<CourseContent.CourseItem>, private val context: Context)
    : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.course_card, parent, false)
        view.setOnClickListener {
            (context as FragmentActivity).supportFragmentManager
                .beginTransaction()
                .remove(CoursesFragment())
                .add(R.id.nav_host_fragment, NoteListFragment())
                .addToBackStack(null)
                .commit()
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