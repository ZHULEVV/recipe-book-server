package com.recipebook.domain.usecase.user

import com.recipebook.domain.entity.Ingredient
import com.recipebook.domain.repository.UserRepository
import java.util.UUID

class GetExcludedIngredientsUseCase(private val userRepository: UserRepository) {
    suspend operator fun invoke(userId: UUID): List<Ingredient> =
        userRepository.getExcludedIngredients(userId)
}
