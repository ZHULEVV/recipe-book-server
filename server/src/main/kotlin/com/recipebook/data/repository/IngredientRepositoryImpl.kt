package com.recipebook.data.repository

import com.recipebook.data.database.dbQuery
import com.recipebook.data.table.IngredientsTable
import com.recipebook.domain.entity.Ingredient
import com.recipebook.domain.repository.IngredientRepository
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import java.util.UUID

class IngredientRepositoryImpl : IngredientRepository {

    private fun toIngredient(row: ResultRow) = Ingredient(
        id = row[IngredientsTable.id],
        name = row[IngredientsTable.name],
        defaultUnit = row[IngredientsTable.defaultUnit]
    )

    override suspend fun findAll(): List<Ingredient> = dbQuery {
        IngredientsTable.selectAll().orderBy(IngredientsTable.name).map(::toIngredient)
    }

    override suspend fun findById(id: UUID): Ingredient? = dbQuery {
        IngredientsTable.selectAll()
            .where { IngredientsTable.id eq id }
            .singleOrNull()
            ?.let(::toIngredient)
    }

    override suspend fun findByName(name: String): Ingredient? = dbQuery {
        IngredientsTable.selectAll()
            .where { IngredientsTable.name eq name }
            .singleOrNull()
            ?.let(::toIngredient)
    }

    override suspend fun create(name: String, defaultUnit: String): Ingredient = dbQuery {
        val id = UUID.randomUUID()
        IngredientsTable.insert {
            it[IngredientsTable.id] = id
            it[IngredientsTable.name] = name
            it[IngredientsTable.defaultUnit] = defaultUnit
        }
        IngredientsTable.selectAll()
            .where { IngredientsTable.id eq id }
            .single()
            .let(::toIngredient)
    }
}
