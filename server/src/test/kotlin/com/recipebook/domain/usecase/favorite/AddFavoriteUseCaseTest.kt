package com.recipebook.domain.usecase.favorite

import com.recipebook.domain.exception.NotFoundException
import com.recipebook.domain.repository.FavoriteRepository
import com.recipebook.domain.repository.RecipeRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.util.UUID

class AddFavoriteUseCaseTest {

    private val favoriteRepository = mockk<FavoriteRepository>()
    private val recipeRepository = mockk<RecipeRepository>()
    private val useCase = AddFavoriteUseCase(favoriteRepository, recipeRepository)

    @Test
    fun `throws NotFoundException when recipe not found`() = runTest {
        val recipeId = UUID.randomUUID()
        coEvery { recipeRepository.findById(recipeId) } returns null

        assertThrows<NotFoundException> { useCase(UUID.randomUUID(), recipeId) }
    }

    @Test
    fun `adds favorite when recipe exists`() = runTest {
        val userId = UUID.randomUUID()
        val recipeId = UUID.randomUUID()
        coEvery { recipeRepository.findById(recipeId) } returns mockk()
        coEvery { favoriteRepository.add(userId, recipeId) } returns Unit

        useCase(userId, recipeId)

        coVerify { favoriteRepository.add(userId, recipeId) }
    }
}
