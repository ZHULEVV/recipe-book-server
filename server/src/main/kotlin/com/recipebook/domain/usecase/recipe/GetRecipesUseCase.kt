package com.recipebook.domain.usecase.recipe

import com.recipebook.domain.entity.Difficulty
import com.recipebook.domain.entity.Recipe
import com.recipebook.domain.repository.RecipeRepository
import java.util.UUID

data class RecipePage(val items: List<Recipe>, val total: Long, val page: Int, val size: Int)

class GetRecipesUseCase(private val recipeRepository: RecipeRepository) {
    suspend operator fun invoke(page: Int, size: Int, difficulty: Difficulty?, tagIds: List<UUID>): RecipePage {
        val items = recipeRepository.findAll(page, size, difficulty, tagIds)
        val total = recipeRepository.count(difficulty, tagIds)
        return RecipePage(items, total, page, size)
    }
}
