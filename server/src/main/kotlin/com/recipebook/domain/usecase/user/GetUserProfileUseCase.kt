package com.recipebook.domain.usecase.user

import com.recipebook.domain.entity.User
import com.recipebook.domain.exception.NotFoundException
import com.recipebook.domain.repository.UserRepository

class GetUserProfileUseCase(private val userRepository: UserRepository) {
    suspend operator fun invoke(firebaseUid: String): User =
        userRepository.findByFirebaseUid(firebaseUid)
            ?: throw NotFoundException("User", firebaseUid)
}
