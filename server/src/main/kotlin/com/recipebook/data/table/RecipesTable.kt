package com.recipebook.data.table

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.kotlin.datetime.timestamp

object RecipesTable : Table("recipes") {
    val id = uuid("id")
    val title = varchar("title", 255)
    val description = text("description")
    val cookingTimeMin = integer("cooking_time_min")
    val activeTimeMin = integer("active_time_min")
    val difficulty = varchar("difficulty", 10)
    val baseServings = integer("base_servings")
    val caloriesPer100g = double("calories_per_100g").nullable()
    val proteinPer100g = double("protein_per_100g").nullable()
    val fatPer100g = double("fat_per_100g").nullable()
    val carbsPer100g = double("carbs_per_100g").nullable()
    val imageUrl = varchar("image_url", 512).nullable()
    val authorId = uuid("author_id").references(UsersTable.id)
    val isPublished = bool("is_published")
    val createdAt = timestamp("created_at")

    override val primaryKey = PrimaryKey(id)
}
