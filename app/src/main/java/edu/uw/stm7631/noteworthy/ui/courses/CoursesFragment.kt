package edu.uw.stm7631.noteworthy.ui.courses

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import edu.uw.stm7631.noteworthy.CourseContent
import edu.uw.stm7631.noteworthy.RecyclerViewAdapter
import androidx.recyclerview.widget.RecyclerView
import edu.uw.stm7631.noteworthy.R
import kotlinx.android.synthetic.main.fragment_courses.*
import kotlinx.android.synthetic.main.fragment_courses.view.*


class CoursesFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_courses, container, false)
        val recyclerView = root.findViewById(R.id.course_recycle) as RecyclerView
        val context = (getActivity() as Context)
        recyclerView.adapter = RecyclerViewAdapter(CourseContent.ITEMS, context)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        fab.setOnClickListener {
            (context as FragmentActivity).supportFragmentManager
                .beginTransaction()
                .remove(CoursesFragment())
                .add(R.id.nav_host_fragment, CoursesAddFragment())
                .addToBackStack(null)
                .commit()
        }
    }
}