package com.recipebook.domain.usecase.recipe

import com.recipebook.domain.repository.RecipeRepository

class SearchRecipesUseCase(private val recipeRepository: RecipeRepository) {
    suspend operator fun invoke(query: String, page: Int, size: Int): RecipePage {
        val items = recipeRepository.search(query, page, size)
        val total = recipeRepository.countSearch(query)
        return RecipePage(items, total, page, size)
    }
}
