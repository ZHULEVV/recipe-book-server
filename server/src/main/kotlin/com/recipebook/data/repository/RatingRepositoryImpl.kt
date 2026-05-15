package com.recipebook.data.repository

import com.recipebook.data.database.dbQuery
import com.recipebook.data.table.RatingsTable
import com.recipebook.domain.entity.Rating
import com.recipebook.domain.entity.RecipeRating
import com.recipebook.domain.repository.RatingRepository
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import java.util.UUID

class RatingRepositoryImpl : RatingRepository {

    private fun toRating(row: ResultRow) = Rating(
        id = row[RatingsTable.id],
        userId = row[RatingsTable.userId],
        recipeId = row[RatingsTable.recipeId],
        score = row[RatingsTable.score]
    )

    override suspend fun findByUserAndRecipe(userId: UUID, recipeId: UUID): Rating? = dbQuery {
        RatingsTable.selectAll()
            .where { (RatingsTable.userId eq userId) and (RatingsTable.recipeId eq recipeId) }
            .singleOrNull()
            ?.let(::toRating)
    }

    override suspend fun upsert(userId: UUID, recipeId: UUID, score: Int): Rating = dbQuery {
        val existing = RatingsTable.selectAll()
            .where { (RatingsTable.userId eq userId) and (RatingsTable.recipeId eq recipeId) }
            .singleOrNull()

        if (existing != null) {
            RatingsTable.update({
                (RatingsTable.userId eq userId) and (RatingsTable.recipeId eq recipeId)
            }) {
                it[RatingsTable.score] = score
            }
            RatingsTable.selectAll()
                .where { (RatingsTable.userId eq userId) and (RatingsTable.recipeId eq recipeId) }
                .single()
                .let(::toRating)
        } else {
            val id = UUID.randomUUID()
            RatingsTable.insert {
                it[RatingsTable.id] = id
                it[RatingsTable.userId] = userId
                it[RatingsTable.recipeId] = recipeId
                it[RatingsTable.score] = score
            }
            RatingsTable.selectAll()
                .where { RatingsTable.id eq id }
                .single()
                .let(::toRating)
        }
    }

    override suspend fun getRating(recipeId: UUID): RecipeRating = dbQuery {
        val rows = RatingsTable.selectAll().where { RatingsTable.recipeId eq recipeId }.toList()
        val count = rows.size.toLong()
        val avg = if (rows.isEmpty()) null else rows.sumOf { it[RatingsTable.score] }.toDouble() / count
        RecipeRating(averageScore = avg, count = count)
    }
}
