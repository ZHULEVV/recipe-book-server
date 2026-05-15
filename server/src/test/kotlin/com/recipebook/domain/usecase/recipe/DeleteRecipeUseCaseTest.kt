package com.recipebook.domain.usecase.recipe

import com.recipebook.domain.entity.Difficulty
import com.recipebook.domain.entity.Recipe
import com.recipebook.domain.exception.ForbiddenException
import com.recipebook.domain.exception.NotFoundException
import com.recipebook.domain.repository.RecipeRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.time.Instant
import java.util.UUID

class DeleteRecipeUseCaseTest {

    private val repository = mockk<RecipeRepository>()
    private val useCase = DeleteRecipeUseCase(repository)

    private fun recipe(authorId: UUID) = Recipe(
        id = UUID.randomUUID(),
        title = "Test",
        description = "Desc",
        cookingTimeMin = 30,
        activeTimeMin = 15,
        difficulty = Difficulty.EASY,
        baseServings = 4,
        caloriesPer100g = null,
        proteinPer100g = null,
        fatPer100g = null,
        carbsPer100g = null,
        imageUrl = null,
        authorId = authorId,
        isPublished = false,
        createdAt = Instant.now()
    )

    @Test
    fun `throws NotFoundException when recipe not found`() = runTest {
        val id = UUID.randomUUID()
        val requesterId = UUID.randomUUID()
        coEvery { repository.findById(id) } returns null

        assertThrows<NotFoundException> { useCase(id, requesterId) }
    }

    @Test
    fun `throws ForbiddenException when requester is not owner`() = runTest {
        val id = UUID.randomUUID()
        val ownerId = UUID.randomUUID()
        val requesterId = UUID.randomUUID()
        coEvery { repository.findById(id) } returns recipe(ownerId)

        assertThrows<ForbiddenException> { useCase(id, requesterId) }
    }

    @Test
    fun `deletes recipe when requester is owner`() = runTest {
        val ownerId = UUID.randomUUID()
        val recipe = recipe(ownerId)
        coEvery { repository.findById(recipe.id) } returns recipe
        coEvery { repository.delete(recipe.id) } returns Unit

        useCase(recipe.id, ownerId)

        coVerify { repository.delete(recipe.id) }
    }
}
