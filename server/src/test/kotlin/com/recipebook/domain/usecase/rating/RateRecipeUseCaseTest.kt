package com.recipebook.domain.usecase.rating

import com.recipebook.domain.entity.Rating
import com.recipebook.domain.exception.NotFoundException
import com.recipebook.domain.exception.ValidationException
import com.recipebook.domain.repository.RatingRepository
import com.recipebook.domain.repository.RecipeRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.util.UUID

class RateRecipeUseCaseTest {

    private val ratingRepository = mockk<RatingRepository>()
    private val recipeRepository = mockk<RecipeRepository>()
    private val useCase = RateRecipeUseCase(ratingRepository, recipeRepository)

    @Test
    fun `throws ValidationException when score is below 1`() = runTest {
        assertThrows<ValidationException> {
            useCase(UUID.randomUUID(), UUID.randomUUID(), 0)
        }
    }

    @Test
    fun `throws ValidationException when score is above 5`() = runTest {
        assertThrows<ValidationException> {
            useCase(UUID.randomUUID(), UUID.randomUUID(), 6)
        }
    }

    @Test
    fun `throws NotFoundException when recipe not found`() = runTest {
        val recipeId = UUID.randomUUID()
        coEvery { recipeRepository.findById(recipeId) } returns null

        assertThrows<NotFoundException> {
            useCase(UUID.randomUUID(), recipeId, 4)
        }
    }

    @Test
    fun `saves rating when score is valid and recipe exists`() = runTest {
        val userId = UUID.randomUUID()
        val recipeId = UUID.randomUUID()
        val rating = Rating(UUID.randomUUID(), userId, recipeId, 4)
        coEvery { recipeRepository.findById(recipeId) } returns mockk()
        coEvery { ratingRepository.upsert(userId, recipeId, 4) } returns rating

        val result = useCase(userId, recipeId, 4)

        assertEquals(4, result.score)
    }
}
