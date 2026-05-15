package com.recipebook.domain.usecase.recipe

import com.recipebook.domain.entity.Recipe
import com.recipebook.domain.repository.CreateRecipeData
import com.recipebook.domain.repository.RecipeRepository

class CreateRecipeUseCase(private val recipeRepository: RecipeRepository) {
    suspend operator fun invoke(data: CreateRecipeData): Recipe =
        recipeRepository.create(data)
}
