package com.recipebook.domain.repository

import com.recipebook.domain.entity.Ingredient
import com.recipebook.domain.entity.User
import java.util.UUID

interface UserRepository {
    suspend fun findByFirebaseUid(firebaseUid: String): User?
    suspend fun findById(id: UUID): User?
    suspend fun upsert(firebaseUid: String, email: String, displayName: String?): User
    suspend fun update(id: UUID, displayName: String?): User
    suspend fun getExcludedIngredients(userId: UUID): List<Ingredient>
    suspend fun addExcludedIngredient(userId: UUID, ingredientId: UUID)
    suspend fun removeExcludedIngredient(userId: UUID, ingredientId: UUID)
}
