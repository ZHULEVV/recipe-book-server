package com.recipebook.domain.usecase.user

import com.recipebook.domain.entity.User
import com.recipebook.domain.repository.UserRepository

class UpsertUserUseCase(private val userRepository: UserRepository) {
    suspend operator fun invoke(firebaseUid: String, email: String, displayName: String?): User =
        userRepository.upsert(firebaseUid, email, displayName)
}
