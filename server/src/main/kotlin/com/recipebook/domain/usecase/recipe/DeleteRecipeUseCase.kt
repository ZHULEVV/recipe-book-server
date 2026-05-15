package com.recipebook.domain.usecase.recipe

import com.recipebook.domain.exception.ForbiddenException
import com.recipebook.domain.exception.NotFoundException
import com.recipebook.domain.repository.RecipeRepository
import java.util.UUID

class DeleteRecipeUseCase(private val recipeRepository: RecipeRepository) {
    suspend operator fun invoke(id: UUID, requesterId: UUID) {
        val existing = recipeRepository.findById(id) ?: throw NotFoundException("Recipe", id)
        if (existing.authorId != requesterId) throw ForbiddenException()
        recipeRepository.delete(id)
    }
}
