package com.recipebook.data.table

import org.jetbrains.exposed.sql.Table

object UserExcludedIngredientsTable : Table("user_excluded_ingredients") {
    val userId = uuid("user_id").references(UsersTable.id)
    val ingredientId = uuid("ingredient_id").references(IngredientsTable.id)

    override val primaryKey = PrimaryKey(userId, ingredientId)
}
