package com.recipebook.domain.usecase.mealplan

import com.recipebook.domain.repository.MealPlanRepository
import com.recipebook.domain.repository.RecipeRepository
import java.time.LocalDate
import java.util.UUID

data class ShoppingListItem(
    val ingredientName: String,
    val totalAmount: Double,
    val unit: String
)

class GetShoppingListUseCase(
    private val mealPlanRepository: MealPlanRepository,
    private val recipeRepository: RecipeRepository
) {
    suspend operator fun invoke(userId: UUID, from: LocalDate, to: LocalDate): List<ShoppingListItem> {
        val entries = mealPlanRepository.findByUserAndDateRange(userId, from, to)
        if (entries.isEmpty()) return emptyList()

        val recipeIds = entries.map { it.recipeId }.distinct()
        val recipes = recipeRepository.findByIds(recipeIds)

        return recipes
            .flatMap { it.ingredients }
            .groupBy { "${it.ingredient.name}||${it.unit}" }
            .map { (key, items) ->
                val parts = key.split("||")
                ShoppingListItem(
                    ingredientName = parts[0],
                    totalAmount = items.sumOf { it.amount },
                    unit = parts[1]
                )
            }
            .sortedBy { it.ingredientName }
    }
}
