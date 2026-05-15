package com.recipebook.data.repository

import com.recipebook.data.database.dbQuery
import com.recipebook.data.table.*
import com.recipebook.domain.entity.*
import com.recipebook.domain.repository.*
import kotlinx.datetime.Clock
import kotlinx.datetime.toJavaInstant
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import java.util.UUID

class RecipeRepositoryImpl : RecipeRepository {

    private fun toIngredient(row: ResultRow) = Ingredient(
        id = row[IngredientsTable.id],
        name = row[IngredientsTable.name],
        defaultUnit = row[IngredientsTable.defaultUnit]
    )

    private fun toRecipeIngredient(row: ResultRow) = RecipeIngredient(
        ingredient = toIngredient(row),
        amount = row[RecipeIngredientsTable.amount],
        unit = row[RecipeIngredientsTable.unit]
    )

    private fun toTag(row: ResultRow) = Tag(
        id = row[TagsTable.id],
        name = row[TagsTable.name],
        category = TagCategory.valueOf(row[TagsTable.category])
    )

    private fun toStep(row: ResultRow) = Step(
        id = row[StepsTable.id],
        recipeId = row[StepsTable.recipeId],
        stepNumber = row[StepsTable.stepNumber],
        content = row[StepsTable.content]
    )

    private fun loadRelations(recipeIds: List<UUID>): Map<UUID, Triple<List<RecipeIngredient>, List<Tag>, List<Step>>> {
        if (recipeIds.isEmpty()) return emptyMap()

        val ingredientsMap = (RecipeIngredientsTable innerJoin IngredientsTable)
            .selectAll()
            .where { RecipeIngredientsTable.recipeId inList recipeIds }
            .groupBy({ it[RecipeIngredientsTable.recipeId] }, ::toRecipeIngredient)

        val tagsMap = (RecipeTagsTable innerJoin TagsTable)
            .selectAll()
            .where { RecipeTagsTable.recipeId inList recipeIds }
            .groupBy({ it[RecipeTagsTable.recipeId] }, ::toTag)

        val stepsMap = StepsTable.selectAll()
            .where { StepsTable.recipeId inList recipeIds }
            .orderBy(StepsTable.stepNumber)
            .groupBy({ it[StepsTable.recipeId] }, ::toStep)

        return recipeIds.associateWith { id ->
            Triple(
                ingredientsMap[id] ?: emptyList(),
                tagsMap[id] ?: emptyList(),
                stepsMap[id] ?: emptyList()
            )
        }
    }

    private fun toRecipe(row: ResultRow, relations: Triple<List<RecipeIngredient>, List<Tag>, List<Step>>) = Recipe(
        id = row[RecipesTable.id],
        title = row[RecipesTable.title],
        description = row[RecipesTable.description],
        cookingTimeMin = row[RecipesTable.cookingTimeMin],
        activeTimeMin = row[RecipesTable.activeTimeMin],
        difficulty = Difficulty.valueOf(row[RecipesTable.difficulty]),
        baseServings = row[RecipesTable.baseServings],
        caloriesPer100g = row[RecipesTable.caloriesPer100g],
        proteinPer100g = row[RecipesTable.proteinPer100g],
        fatPer100g = row[RecipesTable.fatPer100g],
        carbsPer100g = row[RecipesTable.carbsPer100g],
        imageUrl = row[RecipesTable.imageUrl],
        authorId = row[RecipesTable.authorId],
        isPublished = row[RecipesTable.isPublished],
        createdAt = row[RecipesTable.createdAt].toJavaInstant(),
        ingredients = relations.first,
        tags = relations.second,
        steps = relations.third
    )

    fun findByIds(ids: List<UUID>): List<Recipe> {
        val rows = RecipesTable.selectAll()
            .where { RecipesTable.id inList ids }
            .toList()
        val relations = loadRelations(ids)
        return rows.map { row ->
            val id = row[RecipesTable.id]
            toRecipe(row, relations[id] ?: Triple(emptyList(), emptyList(), emptyList()))
        }
    }

    override suspend fun findAll(page: Int, size: Int, difficulty: Difficulty?, tagIds: List<UUID>): List<Recipe> = dbQuery {
        val rows = RecipesTable.selectAll()
            .where {
                var cond: Op<Boolean> = RecipesTable.isPublished eq true
                if (difficulty != null) cond = cond and (RecipesTable.difficulty eq difficulty.name)
                if (tagIds.isNotEmpty()) cond = cond and (RecipesTable.id inSubQuery
                        RecipeTagsTable.select(RecipeTagsTable.recipeId)
                            .where { RecipeTagsTable.tagId inList tagIds })
                cond
            }
            .orderBy(RecipesTable.createdAt, SortOrder.DESC)
            .limit(size).offset(page.toLong() * size)
            .toList()

        val recipeIds = rows.map { it[RecipesTable.id] }
        val relations = loadRelations(recipeIds)
        rows.map { row ->
            val id = row[RecipesTable.id]
            toRecipe(row, relations[id] ?: Triple(emptyList(), emptyList(), emptyList()))
        }
    }

    override suspend fun count(difficulty: Difficulty?, tagIds: List<UUID>): Long = dbQuery {
        RecipesTable.selectAll()
            .where {
                var cond: Op<Boolean> = RecipesTable.isPublished eq true
                if (difficulty != null) cond = cond and (RecipesTable.difficulty eq difficulty.name)
                if (tagIds.isNotEmpty()) cond = cond and (RecipesTable.id inSubQuery
                        RecipeTagsTable.select(RecipeTagsTable.recipeId)
                            .where { RecipeTagsTable.tagId inList tagIds })
                cond
            }
            .count()
    }

    private fun fetchById(id: UUID): Recipe? {
        val row = RecipesTable.selectAll()
            .where { RecipesTable.id eq id }
            .singleOrNull() ?: return null
        val relations = loadRelations(listOf(id))
        return toRecipe(row, relations[id] ?: Triple(emptyList(), emptyList(), emptyList()))
    }

    override suspend fun findById(id: UUID): Recipe? = dbQuery { fetchById(id) }

    override suspend fun findByAuthorId(authorId: UUID, page: Int, size: Int): List<Recipe> = dbQuery {
        val rows = RecipesTable.selectAll()
            .where { RecipesTable.authorId eq authorId }
            .orderBy(RecipesTable.createdAt, SortOrder.DESC)
            .limit(size).offset(page.toLong() * size)
            .toList()

        val recipeIds = rows.map { it[RecipesTable.id] }
        val relations = loadRelations(recipeIds)
        rows.map { row ->
            val id = row[RecipesTable.id]
            toRecipe(row, relations[id] ?: Triple(emptyList(), emptyList(), emptyList()))
        }
    }

    override suspend fun countByAuthorId(authorId: UUID): Long = dbQuery {
        RecipesTable.selectAll().where { RecipesTable.authorId eq authorId }.count()
    }

    override suspend fun create(recipe: CreateRecipeData): Recipe = dbQuery {
        val id = UUID.randomUUID()
        RecipesTable.insert {
            it[RecipesTable.id] = id
            it[RecipesTable.title] = recipe.title
            it[RecipesTable.description] = recipe.description
            it[RecipesTable.cookingTimeMin] = recipe.cookingTimeMin
            it[RecipesTable.activeTimeMin] = recipe.activeTimeMin
            it[RecipesTable.difficulty] = recipe.difficulty.name
            it[RecipesTable.baseServings] = recipe.baseServings
            it[RecipesTable.caloriesPer100g] = recipe.caloriesPer100g
            it[RecipesTable.proteinPer100g] = recipe.proteinPer100g
            it[RecipesTable.fatPer100g] = recipe.fatPer100g
            it[RecipesTable.carbsPer100g] = recipe.carbsPer100g
            it[RecipesTable.imageUrl] = recipe.imageUrl
            it[RecipesTable.authorId] = recipe.authorId
            it[RecipesTable.isPublished] = false
            it[RecipesTable.createdAt] = Clock.System.now()
        }
        saveRelations(id, recipe.ingredientIds, recipe.tagIds, recipe.steps)
        fetchById(id)!!
    }

    override suspend fun update(id: UUID, recipe: UpdateRecipeData): Recipe = dbQuery {
        RecipesTable.update({ RecipesTable.id eq id }) {
            it[RecipesTable.title] = recipe.title
            it[RecipesTable.description] = recipe.description
            it[RecipesTable.cookingTimeMin] = recipe.cookingTimeMin
            it[RecipesTable.activeTimeMin] = recipe.activeTimeMin
            it[RecipesTable.difficulty] = recipe.difficulty.name
            it[RecipesTable.baseServings] = recipe.baseServings
            it[RecipesTable.caloriesPer100g] = recipe.caloriesPer100g
            it[RecipesTable.proteinPer100g] = recipe.proteinPer100g
            it[RecipesTable.fatPer100g] = recipe.fatPer100g
            it[RecipesTable.carbsPer100g] = recipe.carbsPer100g
            it[RecipesTable.imageUrl] = recipe.imageUrl
        }
        RecipeIngredientsTable.deleteWhere { RecipeIngredientsTable.recipeId eq id }
        RecipeTagsTable.deleteWhere { RecipeTagsTable.recipeId eq id }
        StepsTable.deleteWhere { StepsTable.recipeId eq id }
        saveRelations(id, recipe.ingredientIds, recipe.tagIds, recipe.steps)
        fetchById(id)!!
    }

    override suspend fun delete(id: UUID): Unit = dbQuery {
        RecipesTable.deleteWhere { RecipesTable.id eq id }
    }

    override suspend fun publish(id: UUID): Recipe = dbQuery {
        RecipesTable.update({ RecipesTable.id eq id }) {
            it[RecipesTable.isPublished] = true
        }
        fetchById(id)!!
    }

    private fun saveRelations(
        recipeId: UUID,
        ingredients: List<IngredientEntry>,
        tagIds: List<UUID>,
        steps: List<StepEntry>
    ) {
        ingredients.forEach { entry ->
            RecipeIngredientsTable.insert {
                it[RecipeIngredientsTable.recipeId] = recipeId
                it[RecipeIngredientsTable.ingredientId] = entry.ingredientId
                it[RecipeIngredientsTable.amount] = entry.amount
                it[RecipeIngredientsTable.unit] = entry.unit
            }
        }
        tagIds.forEach { tagId ->
            RecipeTagsTable.insert {
                it[RecipeTagsTable.recipeId] = recipeId
                it[RecipeTagsTable.tagId] = tagId
            }
        }
        steps.forEach { step ->
            StepsTable.insert {
                it[StepsTable.id] = UUID.randomUUID()
                it[StepsTable.recipeId] = recipeId
                it[StepsTable.stepNumber] = step.stepNumber
                it[StepsTable.content] = step.content
            }
        }
    }
}
