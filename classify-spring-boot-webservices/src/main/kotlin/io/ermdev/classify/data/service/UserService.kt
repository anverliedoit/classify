package io.ermdev.classify.data.service

import io.ermdev.classify.data.entity.Student
import io.ermdev.classify.data.entity.Teacher
import io.ermdev.classify.data.entity.User
import io.ermdev.classify.data.repository.UserRepository
import io.ermdev.classify.exception.EntityException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserService(@Autowired var userRepository: UserRepository) {

    fun findAll(): List<User> {
        val users: List<User>? = userRepository.findAll()
        return users ?: throw EntityException("No user found")
    }

    fun findById(id: Long): User {
        val user: User? = userRepository.findById(id).orElse(User())
        return user ?: throw EntityException("No user found")
    }

    fun findByUsername(username: String): User {
        val user: User? = userRepository.findByUsername(username)
        return user ?: throw EntityException("No user found")
    }

    fun findStudent(userId: Long): Student {
        val student: Student? = userRepository.findStudent(userId)
        return student ?: throw EntityException("No student found")
    }

    fun findTeacher(userId: Long): Teacher {
        val teacher: Teacher? = userRepository.findTeacher(userId)
        return teacher ?: throw EntityException("No teacher found")
    }
}