package com.recipebook.data.table

import org.jetbrains.exposed.sql.Table

object RecipeTagsTable : Table("recipe_tags") {
    val recipeId = uuid("recipe_id").references(RecipesTable.id)
    val tagId = uuid("tag_id").references(TagsTable.id)

    override val primaryKey = PrimaryKey(recipeId, tagId)
}
