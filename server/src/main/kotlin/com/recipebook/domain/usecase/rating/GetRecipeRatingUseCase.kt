package com.recipebook.domain.usecase.rating

import com.recipebook.domain.entity.RecipeRating
import com.recipebook.domain.repository.RatingRepository
import java.util.UUID

class GetRecipeRatingUseCase(private val ratingRepository: RatingRepository) {
    suspend operator fun invoke(recipeId: UUID): RecipeRating =
        ratingRepository.getRating(recipeId)
}
