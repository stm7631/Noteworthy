package edu.uw.stm7631.noteworthy.ui.courses

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import edu.uw.stm7631.noteworthy.R

class CoursesFragment : Fragment() {

    private lateinit var coursesViewModel: CoursesViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        coursesViewModel =
            ViewModelProviders.of(this).get(CoursesViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_notes, container, false)
        val textView: TextView = root.findViewById(R.id.text_notes)
        coursesViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }
}