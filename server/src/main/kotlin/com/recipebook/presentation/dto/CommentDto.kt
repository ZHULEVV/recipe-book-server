package com.recipebook.presentation.dto

import com.recipebook.domain.entity.Comment
import kotlinx.serialization.Serializable

@Serializable
data class CommentResponse(
    val id: String,
    val userId: String,
    val recipeId: String,
    val text: String,
    val createdAt: String
)

@Serializable
data class CommentPageResponse(val items: List<CommentResponse>, val total: Long, val page: Int, val size: Int)

@Serializable
data class AddCommentRequest(val text: String)

fun Comment.toResponse() = CommentResponse(
    id = id.toString(),
    userId = userId.toString(),
    recipeId = recipeId.toString(),
    text = text,
    createdAt = createdAt.toString()
)
