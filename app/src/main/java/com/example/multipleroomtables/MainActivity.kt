package com.example.multipleroomtables

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.multipleroomtables.entity.database.SchoolDatabase
import com.example.multipleroomtables.entity.relations.StudentSubjectCrossRef
import com.example.multipleroomtables.entity.tables.Director
import com.example.multipleroomtables.entity.tables.School
import com.example.multipleroomtables.entity.tables.Student
import com.example.multipleroomtables.entity.tables.Subject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dao = SchoolDatabase.getInstance(this).schoolDao

        val directors = listOf(
            Director("Mike Litoris", "Jake Wharton School"),
            Director("Jack Goff", "Kotlin School"),
            Director("Chris P. Chicken", "JetBrains School")
        )
        val schools = listOf(
            School("Jake Wharton School"),
            School("Kotlin School"),
            School("JetBrains School")
        )
        val subjects = listOf(
            Subject("Dating for programmers"),
            Subject("Avoiding depression"),
            Subject("Bug Fix Meditation"),
            Subject("Logcat for Newbies"),
            Subject("How to use Google")
        )
        val students = listOf(
            Student("Beff Jezos", 2, "Kotlin School"),
            Student("Mark Suckerberg", 5, "Jake Wharton School"),
            Student("Gill Bates", 8, "Kotlin School"),
            Student("Donny Jepp", 1, "Kotlin School"),
            Student("Hom Tanks", 2, "JetBrains School")
        )
        val studentSubjectRelations = listOf(
            StudentSubjectCrossRef("Beff Jezos", "Dating for programmers"),
            StudentSubjectCrossRef("Beff Jezos", "Avoiding depression"),
            StudentSubjectCrossRef("Beff Jezos", "Bug Fix Meditation"),
            StudentSubjectCrossRef("Beff Jezos", "Logcat for Newbies"),
            StudentSubjectCrossRef("Mark Suckerberg", "Dating for programmers"),
            StudentSubjectCrossRef("Gill Bates", "How to use Google"),
            StudentSubjectCrossRef("Donny Jepp", "Logcat for Newbies"),
            StudentSubjectCrossRef("Hom Tanks", "Avoiding depression"),
            StudentSubjectCrossRef("Hom Tanks", "Dating for programmers")
        )
        GlobalScope.launch {
            withContext(Dispatchers.IO) {
                directors.forEach { dao.insertDirector(it) }
                schools.forEach { dao.insertSchool(it) }
                subjects.forEach { dao.insertSubject(it) }
                students.forEach { dao.insertStudent(it) }
                studentSubjectRelations.forEach { dao.insertStudentSubjectCrossRef(it) }

                val schoolWithDirector = dao.getSchoolAndDirectorWithSchoolName("Kotlin School")
                Log.d("schoolWithDirectorTAG",schoolWithDirector.toString())

                val schoolWithStudents = dao.getSchoolWithStudents("Kotlin School")
                Log.d("schoolWithStudentsTAG",schoolWithStudents.toString())

                val getStudentSubjects = dao.getStudentsOfSubject("Avoiding depression")
                Log.d("getStudentSubjectsTAG",getStudentSubjects.toString())

                val getSubjectsOfStudent = dao.getSubjectsOfStudent("Beff Jezos")
                Log.d("getSubjectsOfStudentTAG",getSubjectsOfStudent.toString())

                 }
            runOnUiThread {
                // Update UI components here
            }
        }
    }
}