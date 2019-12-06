package edu.uw.stm7631.noteworthy.ui.courses

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import edu.uw.stm7631.noteworthy.AddRecyclerViewAdapter
import edu.uw.stm7631.noteworthy.CourseContent
import edu.uw.stm7631.noteworthy.R


class CoursesAddFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_add_courses, container, false)
        val recyclerView = root.findViewById(R.id.course_recycle) as RecyclerView  //creates recycler view from xml
        recyclerView.adapter = AddRecyclerViewAdapter(CourseContent.ITEMS, getActivity() as Context)
        recyclerView.layoutManager = LinearLayoutManager(activity) //sets linear layout to recycler view.
        return root
    }
}