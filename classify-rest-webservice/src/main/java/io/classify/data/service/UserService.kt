package io.classify.data.service

import io.classify.data.entity.User
import io.classify.data.repository.UserRepository
import io.classify.exception.EntityException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserService(@Autowired var userRepository: UserRepository) {

    fun findById(id: Long): User {
        val user: User? = userRepository.findById(id)
        return user ?: throw EntityException("No user found")
    }

    fun findAll(): List<User> {
        val users: List<User>? = userRepository.findAll()
        return users ?: throw EntityException("No user found")
    }

    fun save(user: User) = userRepository.save(user)

    fun delete(user: User) = userRepository.delete(user)

    fun delete(id: Long) = userRepository.delete(id)
}