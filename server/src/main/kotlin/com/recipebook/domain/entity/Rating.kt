package com.recipebook.domain.entity

import java.util.UUID

data class Rating(
    val id: UUID,
    val userId: UUID,
    val recipeId: UUID,
    val score: Int
)

data class RecipeRating(
    val averageScore: Double?,
    val count: Long
)
