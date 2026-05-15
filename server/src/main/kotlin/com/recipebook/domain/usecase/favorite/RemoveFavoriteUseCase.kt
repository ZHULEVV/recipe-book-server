package com.recipebook.domain.usecase.favorite

import com.recipebook.domain.repository.FavoriteRepository
import java.util.UUID

class RemoveFavoriteUseCase(private val favoriteRepository: FavoriteRepository) {
    suspend operator fun invoke(userId: UUID, recipeId: UUID) =
        favoriteRepository.remove(userId, recipeId)
}
