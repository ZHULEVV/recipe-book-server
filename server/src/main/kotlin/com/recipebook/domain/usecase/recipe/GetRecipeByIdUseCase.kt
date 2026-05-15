package com.recipebook.domain.usecase.recipe

import com.recipebook.domain.entity.Recipe
import com.recipebook.domain.exception.NotFoundException
import com.recipebook.domain.repository.RecipeRepository
import java.util.UUID

class GetRecipeByIdUseCase(private val recipeRepository: RecipeRepository) {
    suspend operator fun invoke(id: UUID): Recipe =
        recipeRepository.findById(id) ?: throw NotFoundException("Recipe", id)
}
