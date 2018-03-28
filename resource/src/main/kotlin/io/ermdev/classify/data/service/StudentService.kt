package io.ermdev.classify.data.service

import io.ermdev.classify.data.entity.Lesson
import io.ermdev.classify.data.entity.Role
import io.ermdev.classify.data.entity.Student
import io.ermdev.classify.data.entity.User
import io.ermdev.classify.data.repository.StudentRepository
import io.ermdev.classify.exception.EntityException
import org.springframework.stereotype.Service
import org.springframework.util.StringUtils

@Service
class StudentService(private val studentRepository: StudentRepository) {

    fun findAll(): List<Student> = studentRepository.findAll()

    fun findById(id: Long): Student {
        return studentRepository.findById(id)
                .orElseThrow { EntityException("No teacher student with id $id exists!") }
    }

    fun findLessons(id: Long): List<Lesson> = studentRepository.findLessons(studentId = id)

    fun findLesson(studentId: Long, lessonId: Long): Lesson {
        return studentRepository.findLesson(studentId = studentId, lessonId = lessonId)
                ?: throw EntityException("No lesson entity found!")
    }

    fun findUser(id: Long): User {
        return studentRepository.findUser(id)
                ?: throw EntityException("No user entity found!")
    }

    fun save(student: Student) {
        if (StringUtils.isEmpty(student.name)) {
            throw EntityException("name cannot be empty")
        }
        if (!student.name.matches(Regex("^[a-zA-Z0-9]+( [a-zA-Z0-9]+)*$"))) {
            throw EntityException("name cannot contain special characters")
        }
        if (student.number <= 0) {
            throw EntityException("number cannot be zero or less")
        }
        if (student.id == 0L) {
            val role = Role(name = "USER")
            val user = User(username = student.name.toLowerCase(), password = "${student.number}")
            user.roles.add(role)
            student.user = user
        }
        studentRepository.save(student)
    }

    fun deleteById(id: Long) = studentRepository.deleteById(id)
}