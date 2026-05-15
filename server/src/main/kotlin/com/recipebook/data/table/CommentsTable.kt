package com.recipebook.data.table

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.kotlin.datetime.timestamp

object CommentsTable : Table("comments") {
    val id = uuid("id")
    val userId = uuid("user_id").references(UsersTable.id)
    val recipeId = uuid("recipe_id").references(RecipesTable.id)
    val text = text("text")
    val createdAt = timestamp("created_at")

    override val primaryKey = PrimaryKey(id)
}
