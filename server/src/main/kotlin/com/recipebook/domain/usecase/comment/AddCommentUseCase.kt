package com.recipebook.domain.usecase.comment

import com.recipebook.domain.entity.Comment
import com.recipebook.domain.exception.NotFoundException
import com.recipebook.domain.repository.CommentRepository
import com.recipebook.domain.repository.RecipeRepository
import java.util.UUID

class AddCommentUseCase(
    private val commentRepository: CommentRepository,
    private val recipeRepository: RecipeRepository
) {
    suspend operator fun invoke(userId: UUID, recipeId: UUID, text: String): Comment {
        recipeRepository.findById(recipeId) ?: throw NotFoundException("Recipe", recipeId)
        return commentRepository.create(userId, recipeId, text)
    }
}
