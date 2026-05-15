package com.recipebook.domain.usecase.user

import com.recipebook.domain.exception.NotFoundException
import com.recipebook.domain.repository.IngredientRepository
import com.recipebook.domain.repository.UserRepository
import java.util.UUID

class AddExcludedIngredientUseCase(
    private val userRepository: UserRepository,
    private val ingredientRepository: IngredientRepository
) {
    suspend operator fun invoke(userId: UUID, ingredientId: UUID) {
        ingredientRepository.findById(ingredientId) ?: throw NotFoundException("Ingredient", ingredientId)
        userRepository.addExcludedIngredient(userId, ingredientId)
    }
}
