package com.recipebook.domain.usecase.comment

import com.recipebook.domain.entity.Comment
import com.recipebook.domain.repository.CommentRepository
import java.util.UUID

data class CommentPage(val items: List<Comment>, val total: Long, val page: Int, val size: Int)

class GetCommentsUseCase(private val commentRepository: CommentRepository) {
    suspend operator fun invoke(recipeId: UUID, page: Int, size: Int): CommentPage {
        val items = commentRepository.findByRecipeId(recipeId, page, size)
        val total = commentRepository.count(recipeId)
        return CommentPage(items, total, page, size)
    }
}
