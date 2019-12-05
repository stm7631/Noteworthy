package edu.uw.stm7631.noteworthy

import com.google.firebase.auth.FirebaseAuth
import java.util.ArrayList

object CourseContent {
    lateinit var auth: FirebaseAuth

    //dummy content
    val ITEMS: MutableList<CourseItem> = arrayListOf(
        CourseItem("INFO 101", "Social Networking Technologies", "Autumn 2019"),
        CourseItem("INFO 102", "Gender and Information Technology", "Autumn 2019"),
        CourseItem("INFO 180", "Introduction to Data Science", "Autumn 2019"),
        CourseItem("INFO 200", "Intellectual Foundations of Informatics", "Autumn 2019"),
        CourseItem("INFO 201", "Technical Foundations", "Autumn 2019"),
        CourseItem("INFO 300", "Research Methods", "Autumn 2019"),
        CourseItem("INFO 310", "Information Assurance and Cybersecurity", "Autumn 2019"),
        CourseItem("INFO 314", "Computer Networks and Distributed Applications", "Autumn 2019"),
        CourseItem("INFO 330", "Databases and Data Modeling", "Autumn 2019"),
        CourseItem("INFO 340", "Client-Side Development", "Autumn 2019"),
        CourseItem("INFO 350", "Information Ethics and Policy", "Autumn 2019"),
        CourseItem("INFO 360", "Design Methods", "Autumn 2019"),
        CourseItem("INFO 362", "Visual Information Design", "Autumn 2019"),
        CourseItem("INFO 365", "Mobile Application Design", "Autumn 2019"),
        CourseItem("INFO 370", "Core Methods in Data Science", "Autumn 2019"),
        CourseItem("INFO 371", "Advanced Methods in Data Science", "Autumn 2019")
    ,CourseItem("INFO 380", "Information Systems Analysis and Design", "Autumn 2019"),
        CourseItem("INFO 441", "Server-Side Development", "Autumn 2019"),
        CourseItem("INFO 448", "Mobile Development: Android", "Autumn 2019")
    )

    val MYCOURSES: MutableList<CourseItem> = arrayListOf()

    //dummy content
    val NOTES: MutableList<NoteItem> = arrayListOf()

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
