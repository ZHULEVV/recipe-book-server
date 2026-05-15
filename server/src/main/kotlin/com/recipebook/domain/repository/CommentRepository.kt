package com.recipebook.domain.repository

import com.recipebook.domain.entity.Comment
import java.util.UUID

interface CommentRepository {
    suspend fun findByRecipeId(recipeId: UUID, page: Int, size: Int): List<Comment>
    suspend fun count(recipeId: UUID): Long
    suspend fun findById(id: UUID): Comment?
    suspend fun create(userId: UUID, recipeId: UUID, text: String): Comment
    suspend fun delete(id: UUID)
}
