package com.recipebook.data.repository

import com.recipebook.data.database.dbQuery
import com.recipebook.data.table.IngredientsTable
import com.recipebook.data.table.UserExcludedIngredientsTable
import com.recipebook.data.table.UsersTable
import com.recipebook.domain.entity.Ingredient
import com.recipebook.domain.entity.User
import com.recipebook.domain.repository.UserRepository
import kotlinx.datetime.Clock
import kotlinx.datetime.toJavaInstant
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import java.util.UUID

class UserRepositoryImpl : UserRepository {

    private fun toUser(row: ResultRow) = User(
        id = row[UsersTable.id],
        firebaseUid = row[UsersTable.firebaseUid],
        email = row[UsersTable.email],
        displayName = row[UsersTable.displayName],
        isSubscriber = row[UsersTable.isSubscriber],
        createdAt = row[UsersTable.createdAt].toJavaInstant()
    )

    private fun toIngredient(row: ResultRow) = Ingredient(
        id = row[IngredientsTable.id],
        name = row[IngredientsTable.name],
        defaultUnit = row[IngredientsTable.defaultUnit]
    )

    override suspend fun findByFirebaseUid(firebaseUid: String): User? = dbQuery {
        UsersTable.selectAll()
            .where { UsersTable.firebaseUid eq firebaseUid }
            .singleOrNull()
            ?.let(::toUser)
    }

    override suspend fun findById(id: UUID): User? = dbQuery {
        UsersTable.selectAll()
            .where { UsersTable.id eq id }
            .singleOrNull()
            ?.let(::toUser)
    }

    override suspend fun upsert(firebaseUid: String, email: String, displayName: String?): User = dbQuery {
        val existing = UsersTable.selectAll()
            .where { UsersTable.firebaseUid eq firebaseUid }
            .singleOrNull()

        if (existing != null) {
            UsersTable.update({ UsersTable.firebaseUid eq firebaseUid }) {
                it[UsersTable.email] = email
                if (displayName != null) it[UsersTable.displayName] = displayName
            }
            UsersTable.selectAll()
                .where { UsersTable.firebaseUid eq firebaseUid }
                .single()
                .let(::toUser)
        } else {
            val id = UUID.randomUUID()
            UsersTable.insert {
                it[UsersTable.id] = id
                it[UsersTable.firebaseUid] = firebaseUid
                it[UsersTable.email] = email
                it[UsersTable.displayName] = displayName
                it[UsersTable.isSubscriber] = false
                it[UsersTable.createdAt] = Clock.System.now()
            }
            UsersTable.selectAll()
                .where { UsersTable.id eq id }
                .single()
                .let(::toUser)
        }
    }

    override suspend fun update(id: UUID, displayName: String?): User = dbQuery {
        UsersTable.update({ UsersTable.id eq id }) {
            it[UsersTable.displayName] = displayName
        }
        UsersTable.selectAll()
            .where { UsersTable.id eq id }
            .single()
            .let(::toUser)
    }

    override suspend fun getExcludedIngredients(userId: UUID): List<Ingredient> = dbQuery {
        (UserExcludedIngredientsTable innerJoin IngredientsTable)
            .selectAll()
            .where { UserExcludedIngredientsTable.userId eq userId }
            .map(::toIngredient)
    }

    override suspend fun addExcludedIngredient(userId: UUID, ingredientId: UUID): Unit = dbQuery {
        UserExcludedIngredientsTable.insertIgnore {
            it[UserExcludedIngredientsTable.userId] = userId
            it[UserExcludedIngredientsTable.ingredientId] = ingredientId
        }
    }

    override suspend fun removeExcludedIngredient(userId: UUID, ingredientId: UUID): Unit = dbQuery {
        UserExcludedIngredientsTable.deleteWhere {
            (UserExcludedIngredientsTable.userId eq userId) and
                    (UserExcludedIngredientsTable.ingredientId eq ingredientId)
        }
    }
}
