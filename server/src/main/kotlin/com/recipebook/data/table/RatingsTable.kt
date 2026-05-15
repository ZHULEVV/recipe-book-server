package com.recipebook.data.table

import org.jetbrains.exposed.sql.Table

object RatingsTable : Table("ratings") {
    val id = uuid("id")
    val userId = uuid("user_id").references(UsersTable.id)
    val recipeId = uuid("recipe_id").references(RecipesTable.id)
    val score = integer("score")

    override val primaryKey = PrimaryKey(id)
}
