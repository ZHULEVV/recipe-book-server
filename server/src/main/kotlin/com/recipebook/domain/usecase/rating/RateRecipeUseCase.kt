package com.recipebook.domain.usecase.rating

import com.recipebook.domain.entity.Rating
import com.recipebook.domain.exception.NotFoundException
import com.recipebook.domain.exception.ValidationException
import com.recipebook.domain.repository.RatingRepository
import com.recipebook.domain.repository.RecipeRepository
import java.util.UUID

class RateRecipeUseCase(
    private val ratingRepository: RatingRepository,
    private val recipeRepository: RecipeRepository
) {
    suspend operator fun invoke(userId: UUID, recipeId: UUID, score: Int): Rating {
        if (score !in 1..5) throw ValidationException("Score must be between 1 and 5")
        recipeRepository.findById(recipeId) ?: throw NotFoundException("Recipe", recipeId)
        return ratingRepository.upsert(userId, recipeId, score)
    }
}
