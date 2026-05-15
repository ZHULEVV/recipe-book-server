package com.recipebook.presentation.dto

import com.recipebook.domain.entity.MealPlanEntry
import kotlinx.serialization.Serializable

@Serializable
data class MealPlanEntryResponse(
    val id: String,
    val userId: String,
    val recipeId: String,
    val planDate: String,
    val mealType: String
)

@Serializable
data class AddMealPlanEntryRequest(
    val recipeId: String,
    val planDate: String,
    val mealType: String
)

fun MealPlanEntry.toResponse() = MealPlanEntryResponse(
    id = id.toString(),
    userId = userId.toString(),
    recipeId = recipeId.toString(),
    planDate = planDate.toString(),
    mealType = mealType.name
)
