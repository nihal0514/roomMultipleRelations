package com.example.multipleroomtables.entity.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.multipleroomtables.entity.tables.Director
import com.example.multipleroomtables.entity.tables.School

data class SchoolAndDirector(
    @Embedded val school: School,
    @Relation(
        parentColumn = "schoolName",
        entityColumn = "schoolName"
    )
    val director: Director
)