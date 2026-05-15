package com.recipebook.domain.usecase.ingredient

import com.recipebook.domain.entity.Ingredient
import com.recipebook.domain.repository.IngredientRepository

class GetIngredientsUseCase(private val ingredientRepository: IngredientRepository) {
    suspend operator fun invoke(): List<Ingredient> =
        ingredientRepository.findAll()
}
