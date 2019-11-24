package edu.uw.stm7631.noteworthy

import java.util.ArrayList

object CourseContent {

    val ITEMS: MutableList<CourseItem> = arrayListOf(
        CourseItem("INFO 200", "Section A", "Autumn 2019"),
        CourseItem("INFO 300", "Section B", "Autumn 2019"),
        CourseItem("INFO 400", "Section C", "Autumn 2019"),
        CourseItem("INFO 500", "Section D", "Autumn 2019"),
        CourseItem("INFO 600", "Section E", "Autumn 2019"),
        CourseItem("INFO 700", "Section F", "Autumn 2019"),
        CourseItem("INFO 800", "Section G", "Autumn 2019"),
        CourseItem("INFO 900", "Section H", "Autumn 2019")
    )

    // SMS item with contact name, message content, and date
    data class CourseItem(val course: String, val section: String, val date: String) {
        override fun toString(): String = course
    }
}
