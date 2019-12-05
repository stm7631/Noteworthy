package edu.uw.stm7631.noteworthy

import com.google.firebase.auth.FirebaseAuth
import java.util.ArrayList

object CourseContent {
    lateinit var auth: FirebaseAuth

    //dummy content
    val ITEMS: MutableList<CourseItem> = arrayListOf(
        CourseItem("CSE 142", "Intro to Java", "Winter 2020"),
        CourseItem("INFO 101", "Social Networking Technologies", "Winter 2020"),
        CourseItem("INFO 102", "Gender and Information Technology", "Winter 2020"),
        CourseItem("INFO 180", "Introduction to Data Science", "Winter 2020"),
        CourseItem("INFO 200", "Intellectual Foundations of Informatics", "Winter 2020"),
        CourseItem("INFO 201", "Technical Foundations", "Winter 2020"),
        CourseItem("INFO 300", "Research Methods", "Winter 2020"),
        CourseItem("INFO 310", "Information Assurance and Cybersecurity", "Winter 2020"),
        CourseItem("INFO 314", "Computer Networks and Distributed Applications", "Winter 2020"),
        CourseItem("INFO 330", "Databases and Data Modeling", "Winter 2020"),
        CourseItem("INFO 340", "Client-Side Development", "Winter 2020"),
        CourseItem("INFO 350", "Information Ethics and Policy", "Winter 2020"),
        CourseItem("INFO 360", "Design Methods", "Winter 2020"),
        CourseItem("INFO 362", "Visual Information Design", "Winter 2020"),
        CourseItem("INFO 365", "Mobile Application Design", "Winter 2020"),
        CourseItem("INFO 370", "Core Methods in Data Science", "Winter 2020"),
        CourseItem("INFO 371", "Advanced Methods in Data Science", "Winter 2020")
    ,CourseItem("INFO 380", "Information Systems Analysis and Design", "Winter 2020"),
        CourseItem("INFO 441", "Server-Side Development", "Winter 2020"),
        CourseItem("INFO 448", "Mobile Development: Android", "Winter 2020")
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
