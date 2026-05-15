package com.recipebook.domain.repository

import com.recipebook.domain.entity.Rating
import com.recipebook.domain.entity.RecipeRating
import java.util.UUID

interface RatingRepository {
    suspend fun findByUserAndRecipe(userId: UUID, recipeId: UUID): Rating?
    suspend fun upsert(userId: UUID, recipeId: UUID, score: Int): Rating
    suspend fun getRating(recipeId: UUID): RecipeRating
}
