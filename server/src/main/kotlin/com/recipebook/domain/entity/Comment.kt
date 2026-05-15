package com.recipebook.domain.entity

import java.time.Instant
import java.util.UUID

data class Comment(
    val id: UUID,
    val userId: UUID,
    val recipeId: UUID,
    val text: String,
    val createdAt: Instant
)
