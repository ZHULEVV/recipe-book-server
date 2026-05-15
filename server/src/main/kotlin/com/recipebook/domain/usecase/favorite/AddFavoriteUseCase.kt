package com.recipebook.domain.usecase.favorite

import com.recipebook.domain.exception.NotFoundException
import com.recipebook.domain.repository.FavoriteRepository
import com.recipebook.domain.repository.RecipeRepository
import java.util.UUID

class AddFavoriteUseCase(
    private val favoriteRepository: FavoriteRepository,
    private val recipeRepository: RecipeRepository
) {
    suspend operator fun invoke(userId: UUID, recipeId: UUID) {
        recipeRepository.findById(recipeId) ?: throw NotFoundException("Recipe", recipeId)
        favoriteRepository.add(userId, recipeId)
    }
}
