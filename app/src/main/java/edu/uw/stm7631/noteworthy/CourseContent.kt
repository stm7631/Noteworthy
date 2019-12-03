package edu.uw.stm7631.noteworthy

import java.util.ArrayList

object CourseContent {

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

    val ITEMS2: MutableList<CourseItem> = arrayListOf(
        CourseItem("INFO 200", "Professor A", "Autumn 2019"),
        CourseItem("INFO 300", "Professor B", "Autumn 2019"),
        CourseItem("INFO 400", "Professor C", "Autumn 2019"),
        CourseItem("INFO 500", "Professor D", "Autumn 2019"),
        CourseItem("INFO 600", "Professor E", "Autumn 2019"),
        CourseItem("INFO 700", "Professor F", "Autumn 2019"),
        CourseItem("INFO 800", "Professor G", "Autumn 2019"),
        CourseItem("INFO 900", "Professor H", "Autumn 2019")
    )

    // SMS item with contact name, message content, and date
    data class CourseItem(val course: String, val instructor: String, val date: String) {
        override fun toString(): String = course
    }
}
