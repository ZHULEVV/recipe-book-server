package com.recipebook.data.repository

import com.recipebook.data.database.dbQuery
import com.recipebook.data.table.MealPlanEntriesTable
import com.recipebook.domain.entity.MealPlanEntry
import com.recipebook.domain.entity.MealType
import com.recipebook.domain.repository.MealPlanRepository
import kotlinx.datetime.toJavaLocalDate
import kotlinx.datetime.toKotlinLocalDate
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import java.time.LocalDate
import java.util.UUID

class MealPlanRepositoryImpl : MealPlanRepository {

    private fun toEntry(row: ResultRow) = MealPlanEntry(
        id = row[MealPlanEntriesTable.id],
        userId = row[MealPlanEntriesTable.userId],
        recipeId = row[MealPlanEntriesTable.recipeId],
        planDate = row[MealPlanEntriesTable.planDate].toJavaLocalDate(),
        mealType = MealType.valueOf(row[MealPlanEntriesTable.mealType])
    )

    override suspend fun findByUserAndDateRange(userId: UUID, from: LocalDate, to: LocalDate): List<MealPlanEntry> = dbQuery {
        MealPlanEntriesTable.selectAll()
            .where {
                (MealPlanEntriesTable.userId eq userId) and
                        (MealPlanEntriesTable.planDate greaterEq from.toKotlinLocalDate()) and
                        (MealPlanEntriesTable.planDate lessEq to.toKotlinLocalDate())
            }
            .orderBy(MealPlanEntriesTable.planDate)
            .map(::toEntry)
    }

    override suspend fun findById(id: UUID): MealPlanEntry? = dbQuery {
        MealPlanEntriesTable.selectAll()
            .where { MealPlanEntriesTable.id eq id }
            .singleOrNull()
            ?.let(::toEntry)
    }

    override suspend fun add(userId: UUID, recipeId: UUID, planDate: LocalDate, mealType: MealType): MealPlanEntry = dbQuery {
        val id = UUID.randomUUID()
        MealPlanEntriesTable.insert {
            it[MealPlanEntriesTable.id] = id
            it[MealPlanEntriesTable.userId] = userId
            it[MealPlanEntriesTable.recipeId] = recipeId
            it[MealPlanEntriesTable.planDate] = planDate.toKotlinLocalDate()
            it[MealPlanEntriesTable.mealType] = mealType.name
        }
        MealPlanEntriesTable.selectAll()
            .where { MealPlanEntriesTable.id eq id }
            .single()
            .let(::toEntry)
    }

    override suspend fun delete(id: UUID): Unit = dbQuery {
        MealPlanEntriesTable.deleteWhere { MealPlanEntriesTable.id eq id }
    }
}
