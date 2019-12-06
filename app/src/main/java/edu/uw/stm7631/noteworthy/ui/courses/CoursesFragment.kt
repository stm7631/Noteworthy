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
import kotlinx.android.synthetic.main.get_started.*


class CoursesFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        var root = inflater.inflate(R.layout.fragment_courses, container, false)
        val context = (getActivity() as Context)
        val recyclerView = root.findViewById(R.id.course_recycle) as RecyclerView
        recyclerView.adapter = RecyclerViewAdapter(CourseContent.MYCOURSES, context)
        recyclerView.layoutManager = LinearLayoutManager(activity)   //sets linear layout to recycler view.
//        if (CourseContent.MYCOURSES.isEmpty()) {
//            root = inflater.inflate(R.layout.get_started, container, false)
//        }
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
//        if (CourseContent.MYCOURSES.isEmpty()) {
//            button.setOnClickListener {
//                (context as FragmentActivity).supportFragmentManager
//                    .beginTransaction()
//                    .remove(CoursesFragment())
//                    .add(R.id.nav_host_fragment, CoursesAddFragment())
//                    .addToBackStack(null)
//                    .commit()
//            }
//        } else {
            fab.setOnClickListener {         //Sets action for the add courses floating action button.
                (context as FragmentActivity).supportFragmentManager
                    .beginTransaction()
                    .remove(CoursesFragment())
                    .add(R.id.nav_host_fragment, CoursesAddFragment())  //navigates the user to the courses add fragment 
                    .addToBackStack(null)
                    .commit()
            }
//        }
    }
}