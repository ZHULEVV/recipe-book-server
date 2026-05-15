package com.recipebook.domain.entity

import java.util.UUID

enum class TagCategory { DIET, CUISINE, ALLERGEN }

data class Tag(
    val id: UUID,
    val name: String,
    val category: TagCategory
)
