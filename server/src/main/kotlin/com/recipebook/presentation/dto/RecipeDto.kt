package com.recipebook.presentation.dto

import com.recipebook.domain.entity.*
import com.recipebook.domain.repository.CreateRecipeData
import com.recipebook.domain.repository.IngredientEntry
import com.recipebook.domain.repository.StepEntry
import com.recipebook.domain.repository.UpdateRecipeData
import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class RecipeIngredientResponse(
    val ingredientId: String,
    val name: String,
    val defaultUnit: String,
    val amount: Double,
    val unit: String
)

@Serializable
data class StepResponse(val stepNumber: Int, val content: String)

@Serializable
data class RecipeResponse(
    val id: String,
    val title: String,
    val description: String,
    val cookingTimeMin: Int,
    val activeTimeMin: Int,
    val difficulty: String,
    val baseServings: Int,
    val caloriesPer100g: Double?,
    val proteinPer100g: Double?,
    val fatPer100g: Double?,
    val carbsPer100g: Double?,
    val imageUrl: String?,
    val authorId: String,
    val isPublished: Boolean,
    val createdAt: String,
    val ingredients: List<RecipeIngredientResponse>,
    val tags: List<TagResponse>,
    val steps: List<StepResponse>
)

@Serializable
data class RecipePageResponse(val items: List<RecipeResponse>, val total: Long, val page: Int, val size: Int)

@Serializable
data class IngredientEntryRequest(val ingredientId: String, val amount: Double, val unit: String)

@Serializable
data class StepRequest(val stepNumber: Int, val content: String)

@Serializable
data class CreateRecipeRequest(
    val title: String,
    val description: String,
    val cookingTimeMin: Int,
    val activeTimeMin: Int,
    val difficulty: String,
    val baseServings: Int = 4,
    val caloriesPer100g: Double? = null,
    val proteinPer100g: Double? = null,
    val fatPer100g: Double? = null,
    val carbsPer100g: Double? = null,
    val imageUrl: String? = null,
    val ingredients: List<IngredientEntryRequest> = emptyList(),
    val tagIds: List<String> = emptyList(),
    val steps: List<StepRequest> = emptyList()
)

@Serializable
data class UpdateRecipeRequest(
    val title: String,
    val description: String,
    val cookingTimeMin: Int,
    val activeTimeMin: Int,
    val difficulty: String,
    val baseServings: Int,
    val caloriesPer100g: Double? = null,
    val proteinPer100g: Double? = null,
    val fatPer100g: Double? = null,
    val carbsPer100g: Double? = null,
    val imageUrl: String? = null,
    val ingredients: List<IngredientEntryRequest> = emptyList(),
    val tagIds: List<String> = emptyList(),
    val steps: List<StepRequest> = emptyList()
)

fun RecipeIngredient.toResponse() = RecipeIngredientResponse(
    ingredientId = ingredient.id.toString(),
    name = ingredient.name,
    defaultUnit = ingredient.defaultUnit,
    amount = amount,
    unit = unit
)

fun Step.toResponse() = StepResponse(stepNumber = stepNumber, content = content)

fun Recipe.toResponse() = RecipeResponse(
    id = id.toString(),
    title = title,
    description = description,
    cookingTimeMin = cookingTimeMin,
    activeTimeMin = activeTimeMin,
    difficulty = difficulty.name,
    baseServings = baseServings,
    caloriesPer100g = caloriesPer100g,
    proteinPer100g = proteinPer100g,
    fatPer100g = fatPer100g,
    carbsPer100g = carbsPer100g,
    imageUrl = imageUrl,
    authorId = authorId.toString(),
    isPublished = isPublished,
    createdAt = createdAt.toString(),
    ingredients = ingredients.map { it.toResponse() },
    tags = tags.map { it.toResponse() },
    steps = steps.map { it.toResponse() }
)

fun CreateRecipeRequest.toDomain(authorId: UUID) = CreateRecipeData(
    title = title,
    description = description,
    cookingTimeMin = cookingTimeMin,
    activeTimeMin = activeTimeMin,
    difficulty = Difficulty.valueOf(difficulty),
    baseServings = baseServings,
    caloriesPer100g = caloriesPer100g,
    proteinPer100g = proteinPer100g,
    fatPer100g = fatPer100g,
    carbsPer100g = carbsPer100g,
    imageUrl = imageUrl,
    authorId = authorId,
    ingredientIds = ingredients.map { IngredientEntry(UUID.fromString(it.ingredientId), it.amount, it.unit) },
    tagIds = tagIds.map { UUID.fromString(it) },
    steps = steps.map { StepEntry(it.stepNumber, it.content) }
)

fun UpdateRecipeRequest.toDomain() = UpdateRecipeData(
    title = title,
    description = description,
    cookingTimeMin = cookingTimeMin,
    activeTimeMin = activeTimeMin,
    difficulty = Difficulty.valueOf(difficulty),
    baseServings = baseServings,
    caloriesPer100g = caloriesPer100g,
    proteinPer100g = proteinPer100g,
    fatPer100g = fatPer100g,
    carbsPer100g = carbsPer100g,
    imageUrl = imageUrl,
    ingredientIds = ingredients.map { IngredientEntry(UUID.fromString(it.ingredientId), it.amount, it.unit) },
    tagIds = tagIds.map { UUID.fromString(it) },
    steps = steps.map { StepEntry(it.stepNumber, it.content) }
)
