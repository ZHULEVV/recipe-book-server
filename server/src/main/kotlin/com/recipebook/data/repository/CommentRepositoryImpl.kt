package com.recipebook.data.repository

import com.recipebook.data.database.dbQuery
import com.recipebook.data.table.CommentsTable
import com.recipebook.domain.entity.Comment
import com.recipebook.domain.repository.CommentRepository
import kotlinx.datetime.Clock
import kotlinx.datetime.toJavaInstant
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import java.util.UUID

class CommentRepositoryImpl : CommentRepository {

    private fun toComment(row: ResultRow) = Comment(
        id = row[CommentsTable.id],
        userId = row[CommentsTable.userId],
        recipeId = row[CommentsTable.recipeId],
        text = row[CommentsTable.text],
        createdAt = row[CommentsTable.createdAt].toJavaInstant()
    )

    override suspend fun findByRecipeId(recipeId: UUID, page: Int, size: Int): List<Comment> = dbQuery {
        CommentsTable.selectAll()
            .where { CommentsTable.recipeId eq recipeId }
            .orderBy(CommentsTable.createdAt, SortOrder.DESC)
            .limit(size).offset(page.toLong() * size)
            .map(::toComment)
    }

    override suspend fun count(recipeId: UUID): Long = dbQuery {
        CommentsTable.selectAll().where { CommentsTable.recipeId eq recipeId }.count()
    }

    override suspend fun findById(id: UUID): Comment? = dbQuery {
        CommentsTable.selectAll()
            .where { CommentsTable.id eq id }
            .singleOrNull()
            ?.let(::toComment)
    }

    override suspend fun create(userId: UUID, recipeId: UUID, text: String): Comment = dbQuery {
        val id = UUID.randomUUID()
        CommentsTable.insert {
            it[CommentsTable.id] = id
            it[CommentsTable.userId] = userId
            it[CommentsTable.recipeId] = recipeId
            it[CommentsTable.text] = text
            it[CommentsTable.createdAt] = Clock.System.now()
        }
        CommentsTable.selectAll()
            .where { CommentsTable.id eq id }
            .single()
            .let(::toComment)
    }

    override suspend fun delete(id: UUID): Unit = dbQuery {
        CommentsTable.deleteWhere { CommentsTable.id eq id }
    }
}
