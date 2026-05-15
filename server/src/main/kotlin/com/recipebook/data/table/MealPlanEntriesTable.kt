package com.recipebook.data.table

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.kotlin.datetime.date

object MealPlanEntriesTable : Table("meal_plan_entries") {
    val id = uuid("id")
    val userId = uuid("user_id").references(UsersTable.id)
    val recipeId = uuid("recipe_id").references(RecipesTable.id)
    val planDate = date("plan_date")
    val mealType = varchar("meal_type", 20)

    override val primaryKey = PrimaryKey(id)
}
