package com.recipebook.domain.usecase.recipe

import com.recipebook.domain.exception.NotFoundException
import com.recipebook.domain.repository.RecipeRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.util.UUID

class GetRecipeByIdUseCaseTest {

    private val repository = mockk<RecipeRepository>()
    private val useCase = GetRecipeByIdUseCase(repository)

    @Test
    fun `returns recipe when found`() = runTest {
        val id = UUID.randomUUID()
        val recipe = mockk<com.recipebook.domain.entity.Recipe>()
        coEvery { repository.findById(id) } returns recipe

        val result = useCase(id)

        assertEquals(recipe, result)
    }

    @Test
    fun `throws NotFoundException when recipe not found`() = runTest {
        val id = UUID.randomUUID()
        coEvery { repository.findById(id) } returns null

        assertThrows<NotFoundException> { useCase(id) }
    }
}
