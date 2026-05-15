package com.recipebook.data.repository

import com.recipebook.data.database.dbQuery
import com.recipebook.data.table.TagsTable
import com.recipebook.domain.entity.Tag
import com.recipebook.domain.entity.TagCategory
import com.recipebook.domain.repository.TagRepository
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import java.util.UUID

class TagRepositoryImpl : TagRepository {

    private fun toTag(row: ResultRow) = Tag(
        id = row[TagsTable.id],
        name = row[TagsTable.name],
        category = TagCategory.valueOf(row[TagsTable.category])
    )

    override suspend fun findAll(): List<Tag> = dbQuery {
        TagsTable.selectAll().orderBy(TagsTable.name).map(::toTag)
    }

    override suspend fun findById(id: UUID): Tag? = dbQuery {
        TagsTable.selectAll()
            .where { TagsTable.id eq id }
            .singleOrNull()
            ?.let(::toTag)
    }

    override suspend fun findByCategory(category: TagCategory): List<Tag> = dbQuery {
        TagsTable.selectAll()
            .where { TagsTable.category eq category.name }
            .orderBy(TagsTable.name)
            .map(::toTag)
    }
}
