package com.recipebook.domain.repository

import com.recipebook.domain.entity.Tag
import com.recipebook.domain.entity.TagCategory
import java.util.UUID

interface TagRepository {
    suspend fun findAll(): List<Tag>
    suspend fun findById(id: UUID): Tag?
    suspend fun findByCategory(category: TagCategory): List<Tag>
}
