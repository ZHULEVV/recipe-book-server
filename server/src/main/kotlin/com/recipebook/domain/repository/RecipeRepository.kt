package com.recipebook.domain.repository

import com.recipebook.domain.entity.Difficulty
import com.recipebook.domain.entity.Recipe
import java.util.UUID

interface RecipeRepository {
    suspend fun findAll(page: Int, size: Int, difficulty: Difficulty?, tagIds: List<UUID>): List<Recipe>
    suspend fun count(difficulty: Difficulty?, tagIds: List<UUID>): Long
    suspend fun search(query: String, page: Int, size: Int): List<Recipe>
    suspend fun countSearch(query: String): Long
    suspend fun findById(id: UUID): Recipe?
    suspend fun findByAuthorId(authorId: UUID, page: Int, size: Int): List<Recipe>
    suspend fun countByAuthorId(authorId: UUID): Long
    suspend fun create(recipe: CreateRecipeData): Recipe
    suspend fun update(id: UUID, recipe: UpdateRecipeData): Recipe
    suspend fun delete(id: UUID)
    suspend fun publish(id: UUID): Recipe
}

data class CreateRecipeData(
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
    val ingredientIds: List<IngredientEntry>,
    val tagIds: List<UUID>,
    val steps: List<StepEntry>
)

data class UpdateRecipeData(
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
    val ingredientIds: List<IngredientEntry>,
    val tagIds: List<UUID>,
    val steps: List<StepEntry>
)

data class IngredientEntry(val ingredientId: UUID, val amount: Double, val unit: String)
data class StepEntry(val stepNumber: Int, val content: String)
