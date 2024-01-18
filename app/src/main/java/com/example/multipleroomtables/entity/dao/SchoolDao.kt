package com.example.multipleroomtables.entity.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.multipleroomtables.entity.relations.SchoolAndDirector
import com.example.multipleroomtables.entity.relations.SchoolWithStudents
import com.example.multipleroomtables.entity.relations.StudentSubjectCrossRef
import com.example.multipleroomtables.entity.relations.StudentWithSubjects
import com.example.multipleroomtables.entity.relations.SubjectWithStudents
import com.example.multipleroomtables.entity.tables.Director
import com.example.multipleroomtables.entity.tables.School
import com.example.multipleroomtables.entity.tables.Student
import com.example.multipleroomtables.entity.tables.Subject

@Dao
interface SchoolDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insertSchool(school: School)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insertDirector(director: Director)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insertStudent(student: Student)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insertSubject(subject: Subject)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insertStudentSubjectCrossRef(crossRef: StudentSubjectCrossRef)

    @Transaction
    @Query("SELECT * FROM school WHERE schoolName = :schoolName")
     fun getSchoolAndDirectorWithSchoolName(schoolName: String): List<SchoolAndDirector>

    @Transaction
    @Query("SELECT * FROM school WHERE schoolName = :schoolName")
     fun getSchoolWithStudents(schoolName: String): List<SchoolWithStudents>

    @Transaction
    @Query("SELECT * FROM subject WHERE subjectName = :subjectName")
     fun getStudentsOfSubject(subjectName: String): List<SubjectWithStudents>

    @Transaction
    @Query("SELECT * FROM student WHERE studentName = :studentName")
     fun getSubjectsOfStudent(studentName: String): List<StudentWithSubjects>
}