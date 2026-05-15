package com.recipebook.domain.usecase.ingredient

import com.recipebook.domain.entity.Ingredient
import com.recipebook.domain.exception.ConflictException
import com.recipebook.domain.repository.IngredientRepository

class CreateIngredientUseCase(private val ingredientRepository: IngredientRepository) {
    suspend operator fun invoke(name: String, defaultUnit: String): Ingredient {
        if (ingredientRepository.findByName(name) != null) throw ConflictException("Ingredient already exists: $name")
        return ingredientRepository.create(name, defaultUnit)
    }
}
