package com.recipebook.domain.usecase.recipe

import com.recipebook.domain.entity.Recipe
import com.recipebook.domain.repository.RecipeRepository
import java.util.UUID

data class MyRecipePage(val items: List<Recipe>, val total: Long, val page: Int, val size: Int)

class GetMyRecipesUseCase(private val recipeRepository: RecipeRepository) {
    suspend operator fun invoke(authorId: UUID, page: Int, size: Int): MyRecipePage {
        val items = recipeRepository.findByAuthorId(authorId, page, size)
        val total = recipeRepository.countByAuthorId(authorId)
        return MyRecipePage(items, total, page, size)
    }
}
