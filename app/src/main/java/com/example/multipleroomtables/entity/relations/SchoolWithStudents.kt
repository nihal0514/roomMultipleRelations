package com.example.multipleroomtables.entity.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.multipleroomtables.entity.tables.School
import com.example.multipleroomtables.entity.tables.Student

data class SchoolWithStudents(
    @Embedded val school: School,
    @Relation(
        parentColumn = "schoolName",
        entityColumn = "schoolName"
    )
    val students: List<Student>
)