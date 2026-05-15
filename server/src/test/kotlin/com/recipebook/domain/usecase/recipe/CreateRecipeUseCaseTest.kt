package com.recipebook.domain.usecase.recipe

import com.recipebook.domain.entity.Difficulty
import com.recipebook.domain.entity.Recipe
import com.recipebook.domain.repository.CreateRecipeData
import com.recipebook.domain.repository.RecipeRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.time.Instant
import java.util.UUID

class CreateRecipeUseCaseTest {

    private val repository = mockk<RecipeRepository>()
    private val useCase = CreateRecipeUseCase(repository)

    @Test
    fun `creates recipe and returns result from repository`() = runTest {
        val authorId = UUID.randomUUID()
        val data = CreateRecipeData(
            title = "Борщ",
            description = "Украинский борщ",
            cookingTimeMin = 90,
            activeTimeMin = 40,
            difficulty = Difficulty.MEDIUM,
            baseServings = 6,
            caloriesPer100g = null,
            proteinPer100g = null,
            fatPer100g = null,
            carbsPer100g = null,
            imageUrl = null,
            authorId = authorId,
            ingredientIds = emptyList(),
            tagIds = emptyList(),
            steps = emptyList()
        )
        val created = Recipe(
            id = UUID.randomUUID(), title = data.title, description = data.description,
            cookingTimeMin = data.cookingTimeMin, activeTimeMin = data.activeTimeMin,
            difficulty = data.difficulty, baseServings = data.baseServings,
            caloriesPer100g = null, proteinPer100g = null, fatPer100g = null, carbsPer100g = null,
            imageUrl = null, authorId = authorId, isPublished = false, createdAt = Instant.now()
        )
        coEvery { repository.create(data) } returns created

        val result = useCase(data)

        assertEquals(created, result)
        coVerify { repository.create(data) }
    }
}
