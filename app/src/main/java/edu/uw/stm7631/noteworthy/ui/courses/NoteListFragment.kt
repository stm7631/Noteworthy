package edu.uw.stm7631.noteworthy.ui.courses

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import edu.uw.stm7631.noteworthy.CourseContent
import edu.uw.stm7631.noteworthy.RecyclerViewAdapter
import androidx.recyclerview.widget.RecyclerView
import edu.uw.stm7631.noteworthy.NotesRecyclerViewAdapter
import edu.uw.stm7631.noteworthy.R
import edu.uw.stm7631.noteworthy.ui.notes.NotesFragment


class NoteListFragment : Fragment() {

    private var paramData: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            paramData = it.getString("course")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Toast.makeText(context, paramData, Toast.LENGTH_LONG).show()
        val root = inflater.inflate(R.layout.fragment_notelist, container, false)
//        val recyclerView = root.findViewById(R.id.course_recycle) as RecyclerView
//        recyclerView.adapter = NotesRecyclerViewAdapter(CourseContent.NOTES, getActivity() as Context)
//        recyclerView.layoutManager = LinearLayoutManager(activity)
        return root
    }

    companion object {
        @JvmStatic
        fun newInstance(paramData: String) =
            NoteListFragment().apply {
                arguments = Bundle().apply {
                    putString("course", paramData)
                }
            }
    }
}