package com.recipebook.data.table

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.kotlin.datetime.timestamp

object FavoritesTable : Table("favorites") {
    val userId = uuid("user_id").references(UsersTable.id)
    val recipeId = uuid("recipe_id").references(RecipesTable.id)
    val addedAt = timestamp("added_at")

    override val primaryKey = PrimaryKey(userId, recipeId)
}
