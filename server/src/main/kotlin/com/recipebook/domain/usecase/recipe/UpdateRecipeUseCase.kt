package com.recipebook.domain.usecase.recipe

import com.recipebook.domain.entity.Recipe
import com.recipebook.domain.exception.ForbiddenException
import com.recipebook.domain.exception.NotFoundException
import com.recipebook.domain.repository.RecipeRepository
import com.recipebook.domain.repository.UpdateRecipeData
import java.util.UUID

class UpdateRecipeUseCase(private val recipeRepository: RecipeRepository) {
    suspend operator fun invoke(id: UUID, requesterId: UUID, data: UpdateRecipeData): Recipe {
        val existing = recipeRepository.findById(id) ?: throw NotFoundException("Recipe", id)
        if (existing.authorId != requesterId) throw ForbiddenException()
        return recipeRepository.update(id, data)
    }
}
