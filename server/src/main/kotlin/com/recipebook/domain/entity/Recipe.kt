package com.recipebook.domain.entity

import java.time.Instant
import java.util.UUID

enum class Difficulty { EASY, MEDIUM, HARD }

data class RecipeIngredient(
    val ingredient: Ingredient,
    val amount: Double,
    val unit: String
)

data class Recipe(
    val id: UUID,
    val title: String,
    val description: String,
    val cookingTimeMin: Int,
    val activeTimeMin: Int,
    val difficulty: Difficulty,
    val baseServings: Int,
    val caloriesPer100g: Double?,
    val proteinPer100g: Double?,
    val fatPer100g: Double?,
    val carbsPer100g: Double?,
    val imageUrl: String?,
    val authorId: UUID,
    val isPublished: Boolean,
    val createdAt: Instant,
    val ingredients: List<RecipeIngredient> = emptyList(),
    val tags: List<Tag> = emptyList(),
    val steps: List<Step> = emptyList()
)
