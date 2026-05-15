package com.recipebook.domain.repository

import com.recipebook.domain.entity.Recipe
import java.util.UUID

interface FavoriteRepository {
    suspend fun findByUserId(userId: UUID, page: Int, size: Int): List<Recipe>
    suspend fun count(userId: UUID): Long
    suspend fun add(userId: UUID, recipeId: UUID)
    suspend fun remove(userId: UUID, recipeId: UUID)
    suspend fun exists(userId: UUID, recipeId: UUID): Boolean
}
