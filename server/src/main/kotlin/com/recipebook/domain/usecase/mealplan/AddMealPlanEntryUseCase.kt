package com.recipebook.domain.usecase.mealplan

import com.recipebook.domain.entity.MealPlanEntry
import com.recipebook.domain.entity.MealType
import com.recipebook.domain.exception.NotFoundException
import com.recipebook.domain.repository.MealPlanRepository
import com.recipebook.domain.repository.RecipeRepository
import java.time.LocalDate
import java.util.UUID

class AddMealPlanEntryUseCase(
    private val mealPlanRepository: MealPlanRepository,
    private val recipeRepository: RecipeRepository
) {
    suspend operator fun invoke(userId: UUID, recipeId: UUID, planDate: LocalDate, mealType: MealType): MealPlanEntry {
        recipeRepository.findById(recipeId) ?: throw NotFoundException("Recipe", recipeId)
        return mealPlanRepository.add(userId, recipeId, planDate, mealType)
    }
}
