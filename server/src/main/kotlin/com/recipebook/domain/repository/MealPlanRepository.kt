package com.recipebook.domain.repository

import com.recipebook.domain.entity.MealPlanEntry
import com.recipebook.domain.entity.MealType
import java.time.LocalDate
import java.util.UUID

interface MealPlanRepository {
    suspend fun findByUserAndDateRange(userId: UUID, from: LocalDate, to: LocalDate): List<MealPlanEntry>
    suspend fun findById(id: UUID): MealPlanEntry?
    suspend fun add(userId: UUID, recipeId: UUID, planDate: LocalDate, mealType: MealType): MealPlanEntry
    suspend fun delete(id: UUID)
}
