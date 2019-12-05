package edu.uw.stm7631.noteworthy

import com.google.firebase.auth.FirebaseAuth
import java.util.ArrayList

object CourseContent {
    lateinit var auth: FirebaseAuth

    //dummy content
    val ITEMS: MutableList<CourseItem> = arrayListOf(
        CourseItem("INFO 200", "Professor A", "Autumn 2019"),
        CourseItem("INFO 300", "Professor B", "Autumn 2019"),
        CourseItem("INFO 400", "Professor C", "Autumn 2019"),
        CourseItem("INFO 500", "Professor D", "Autumn 2019"),
        CourseItem("INFO 600", "Professor E", "Autumn 2019"),
        CourseItem("INFO 700", "Professor F", "Autumn 2019"),
        CourseItem("INFO 800", "Professor G", "Autumn 2019"),
        CourseItem("INFO 900", "Professor H", "Autumn 2019")
    )

    //dummy content
    val NOTES: MutableList<NoteItem> = arrayListOf(
        NoteItem("NOTE 200", "Student A", "Autumn 2019"),
        NoteItem("NOTE 300", "Student B", "Autumn 2019"),
        NoteItem("NOTE 400", "Student C", "Autumn 2019"),
        NoteItem("NOTE 500", "Student D", "Autumn 2019"),
        NoteItem("NOTE 600", "Student E", "Autumn 2019"),
        NoteItem("NOTE 700", "Student F", "Autumn 2019"),
        NoteItem("NOTE 800", "Student G", "Autumn 2019"),
        NoteItem("NOTE 900", "Student H", "Autumn 2019")
    )

    data class User(val name: String, val email: String) {
        override fun toString(): String = email
    }

    // SMS item with contact name, message content, and date
    data class CourseItem(val course: String, val instructor: String, val date: String) {
        override fun toString(): String = course
    }

    data class NoteItem(val title: String, val author: String, val date: String) {
        override fun toString(): String = title
    }
}
