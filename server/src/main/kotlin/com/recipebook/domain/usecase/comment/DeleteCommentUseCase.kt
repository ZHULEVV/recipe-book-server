package com.recipebook.domain.usecase.comment

import com.recipebook.domain.exception.ForbiddenException
import com.recipebook.domain.exception.NotFoundException
import com.recipebook.domain.repository.CommentRepository
import java.util.UUID

class DeleteCommentUseCase(private val commentRepository: CommentRepository) {
    suspend operator fun invoke(commentId: UUID, requesterId: UUID) {
        val comment = commentRepository.findById(commentId) ?: throw NotFoundException("Comment", commentId)
        if (comment.userId != requesterId) throw ForbiddenException()
        commentRepository.delete(commentId)
    }
}
