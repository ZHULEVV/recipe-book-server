package com.recipebook.domain.usecase.favorite

import com.recipebook.domain.entity.Recipe
import com.recipebook.domain.repository.FavoriteRepository
import java.util.UUID

data class FavoritePage(val items: List<Recipe>, val total: Long, val page: Int, val size: Int)

class GetFavoritesUseCase(private val favoriteRepository: FavoriteRepository) {
    suspend operator fun invoke(userId: UUID, page: Int, size: Int): FavoritePage {
        val items = favoriteRepository.findByUserId(userId, page, size)
        val total = favoriteRepository.count(userId)
        return FavoritePage(items, total, page, size)
    }
}
