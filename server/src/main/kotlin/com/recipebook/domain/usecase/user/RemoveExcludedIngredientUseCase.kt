package com.recipebook.domain.usecase.user

import com.recipebook.domain.repository.UserRepository
import java.util.UUID

class RemoveExcludedIngredientUseCase(private val userRepository: UserRepository) {
    suspend operator fun invoke(userId: UUID, ingredientId: UUID) =
        userRepository.removeExcludedIngredient(userId, ingredientId)
}
