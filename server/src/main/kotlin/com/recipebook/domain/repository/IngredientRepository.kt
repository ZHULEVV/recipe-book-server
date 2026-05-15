package com.recipebook.domain.repository

import com.recipebook.domain.entity.Ingredient
import java.util.UUID

interface IngredientRepository {
    suspend fun findAll(): List<Ingredient>
    suspend fun findById(id: UUID): Ingredient?
    suspend fun findByName(name: String): Ingredient?
    suspend fun create(name: String, defaultUnit: String): Ingredient
}
