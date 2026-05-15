package com.recipebook.domain.usecase.comment

import com.recipebook.domain.entity.Comment
import com.recipebook.domain.exception.ForbiddenException
import com.recipebook.domain.exception.NotFoundException
import com.recipebook.domain.repository.CommentRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.time.Instant
import java.util.UUID

class DeleteCommentUseCaseTest {

    private val repository = mockk<CommentRepository>()
    private val useCase = DeleteCommentUseCase(repository)

    @Test
    fun `throws NotFoundException when comment not found`() = runTest {
        val commentId = UUID.randomUUID()
        coEvery { repository.findById(commentId) } returns null

        assertThrows<NotFoundException> { useCase(commentId, UUID.randomUUID()) }
    }

    @Test
    fun `throws ForbiddenException when requester is not comment owner`() = runTest {
        val commentId = UUID.randomUUID()
        val ownerId = UUID.randomUUID()
        val comment = Comment(commentId, ownerId, UUID.randomUUID(), "text", Instant.now())
        coEvery { repository.findById(commentId) } returns comment

        assertThrows<ForbiddenException> { useCase(commentId, UUID.randomUUID()) }
    }

    @Test
    fun `deletes comment when requester is owner`() = runTest {
        val ownerId = UUID.randomUUID()
        val commentId = UUID.randomUUID()
        val comment = Comment(commentId, ownerId, UUID.randomUUID(), "text", Instant.now())
        coEvery { repository.findById(commentId) } returns comment
        coEvery { repository.delete(commentId) } returns Unit

        useCase(commentId, ownerId)

        coVerify { repository.delete(commentId) }
    }
}
