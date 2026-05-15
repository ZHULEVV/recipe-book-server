package com.recipebook.data.table

import org.jetbrains.exposed.sql.Table

object RecipeIngredientsTable : Table("recipe_ingredients") {
    val recipeId = uuid("recipe_id").references(RecipesTable.id)
    val ingredientId = uuid("ingredient_id").references(IngredientsTable.id)
    val amount = double("amount")
    val unit = varchar("unit", 50)

    override val primaryKey = PrimaryKey(recipeId, ingredientId)
}
