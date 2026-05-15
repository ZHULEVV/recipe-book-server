package com.recipebook.domain.entity

import java.util.UUID

data class Step(
    val id: UUID,
    val recipeId: UUID,
    val stepNumber: Int,
    val content: String
)
