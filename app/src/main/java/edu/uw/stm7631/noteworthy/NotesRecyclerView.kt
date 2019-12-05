package edu.uw.stm7631.noteworthy

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import edu.uw.stm7631.noteworthy.ui.courses.NoteListFragment
import kotlinx.android.synthetic.main.note_card.view.*

class NotesRecyclerViewAdapter(private val values: List<CourseContent.NoteItem>, private val context: Context)
    : RecyclerView.Adapter<NotesRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.note_card, parent, false)
//        view.setOnClickListener {
//            (context as FragmentActivity).supportFragmentManager
//                .beginTransaction()
//                .add(R.id.nav_host_fragment, NoteListFragment())
//                .addToBackStack(null)
//                .commit()
//        }
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.title.text = item.title
        holder.author.text = item.author

        with(holder.itemView) {
            tag = item
        }
    }

    override fun getItemCount() = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.note_name
        val author: TextView = view.note_author
    }
}