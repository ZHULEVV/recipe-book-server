package com.recipebook.domain.usecase.mealplan

import com.recipebook.domain.entity.MealPlanEntry
import com.recipebook.domain.repository.MealPlanRepository
import java.time.LocalDate
import java.util.UUID

class GetMealPlanUseCase(private val mealPlanRepository: MealPlanRepository) {
    suspend operator fun invoke(userId: UUID, from: LocalDate, to: LocalDate): List<MealPlanEntry> =
        mealPlanRepository.findByUserAndDateRange(userId, from, to)
}
