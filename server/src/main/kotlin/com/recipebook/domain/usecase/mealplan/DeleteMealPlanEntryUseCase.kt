package com.recipebook.domain.usecase.mealplan

import com.recipebook.domain.exception.ForbiddenException
import com.recipebook.domain.exception.NotFoundException
import com.recipebook.domain.repository.MealPlanRepository
import java.util.UUID

class DeleteMealPlanEntryUseCase(private val mealPlanRepository: MealPlanRepository) {
    suspend operator fun invoke(entryId: UUID, requesterId: UUID) {
        val entry = mealPlanRepository.findById(entryId) ?: throw NotFoundException("MealPlanEntry", entryId)
        if (entry.userId != requesterId) throw ForbiddenException()
        mealPlanRepository.delete(entryId)
    }
}
