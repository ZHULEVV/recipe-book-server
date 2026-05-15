package com.recipebook.data.repository

import com.recipebook.data.database.dbQuery
import com.recipebook.data.table.FavoritesTable
import com.recipebook.domain.entity.Recipe
import com.recipebook.domain.repository.FavoriteRepository
import kotlinx.datetime.Clock
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import java.util.UUID

class FavoriteRepositoryImpl(
    private val recipeRepositoryImpl: RecipeRepositoryImpl
) : FavoriteRepository {

    override suspend fun findByUserId(userId: UUID, page: Int, size: Int): List<Recipe> = dbQuery {
        val recipeIds = FavoritesTable.selectAll()
            .where { FavoritesTable.userId eq userId }
            .orderBy(FavoritesTable.addedAt, SortOrder.DESC)
            .limit(size).offset(page.toLong() * size)
            .map { it[FavoritesTable.recipeId] }

        if (recipeIds.isEmpty()) return@dbQuery emptyList()
        recipeRepositoryImpl.findByIds(recipeIds)
    }

    override suspend fun count(userId: UUID): Long = dbQuery {
        FavoritesTable.selectAll().where { FavoritesTable.userId eq userId }.count()
    }

    override suspend fun add(userId: UUID, recipeId: UUID): Unit = dbQuery {
        FavoritesTable.insertIgnore {
            it[FavoritesTable.userId] = userId
            it[FavoritesTable.recipeId] = recipeId
            it[FavoritesTable.addedAt] = Clock.System.now()
        }
    }

    override suspend fun remove(userId: UUID, recipeId: UUID): Unit = dbQuery {
        FavoritesTable.deleteWhere {
            (FavoritesTable.userId eq userId) and (FavoritesTable.recipeId eq recipeId)
        }
    }

    override suspend fun exists(userId: UUID, recipeId: UUID): Boolean = dbQuery {
        FavoritesTable.selectAll()
            .where { (FavoritesTable.userId eq userId) and (FavoritesTable.recipeId eq recipeId) }
            .count() > 0
    }
}
