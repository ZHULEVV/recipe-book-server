package com.recipebook.domain.usecase.user

import com.recipebook.domain.entity.User
import com.recipebook.domain.exception.NotFoundException
import com.recipebook.domain.repository.UserRepository
import java.util.UUID

class UpdateUserProfileUseCase(private val userRepository: UserRepository) {
    suspend operator fun invoke(userId: UUID, displayName: String?): User {
        userRepository.findById(userId) ?: throw NotFoundException("User", userId)
        return userRepository.update(userId, displayName)
    }
}
