package edu.uw.stm7631.noteworthy

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.course_card.view.*

// Recycler to bind SMS data to view
class RecyclerViewAdapter(private val values: List<CourseContent.CourseItem>)
    : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.course_card, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.course.text = item.course
        holder.section.text = item.section
        holder.date.text = item.date

        with(holder.itemView) {
            tag = item
        }
    }

    override fun getItemCount() = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val course: TextView = view.course_name
        val section: TextView = view.course_section
        val date: TextView = view.course_date
    }
}