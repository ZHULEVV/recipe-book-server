package com.recipebook.data.table

import org.jetbrains.exposed.sql.Table

object StepsTable : Table("steps") {
    val id = uuid("id")
    val recipeId = uuid("recipe_id").references(RecipesTable.id)
    val stepNumber = integer("step_number")
    val content = text("content")

    override val primaryKey = PrimaryKey(id)
}
