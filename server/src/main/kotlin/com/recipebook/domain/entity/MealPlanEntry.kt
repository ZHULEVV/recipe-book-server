package com.recipebook.domain.entity

import java.time.LocalDate
import java.util.UUID

enum class MealType { BREAKFAST, LUNCH, DINNER, SNACK }

data class MealPlanEntry(
    val id: UUID,
    val userId: UUID,
    val recipeId: UUID,
    val planDate: LocalDate,
    val mealType: MealType
)
