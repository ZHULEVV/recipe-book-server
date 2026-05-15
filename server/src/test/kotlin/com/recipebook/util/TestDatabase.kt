package com.recipebook.util

import com.recipebook.data.table.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

object TestDatabase {
    fun init() {
        Database.connect(
            url = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;MODE=PostgreSQL;DATABASE_TO_LOWER=TRUE;DEFAULT_NULL_ORDERING=HIGH",
            driver = "org.h2.Driver"
        )
        transaction {
            SchemaUtils.create(
                UsersTable,
                IngredientsTable,
                TagsTable,
                RecipesTable,
                RecipeIngredientsTable,
                RecipeTagsTable,
                StepsTable,
                FavoritesTable,
                RatingsTable,
                CommentsTable,
                MealPlanEntriesTable,
                UserExcludedIngredientsTable
            )
        }
    }

    fun clean() {
        transaction {
            SchemaUtils.drop(
                UserExcludedIngredientsTable,
                MealPlanEntriesTable,
                CommentsTable,
                RatingsTable,
                FavoritesTable,
                StepsTable,
                RecipeTagsTable,
                RecipeIngredientsTable,
                RecipesTable,
                TagsTable,
                IngredientsTable,
                UsersTable
            )
        }
    }
}
