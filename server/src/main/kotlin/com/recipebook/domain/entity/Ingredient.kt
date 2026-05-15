package com.recipebook.domain.entity

import java.util.UUID

data class Ingredient(
    val id: UUID,
    val name: String,
    val defaultUnit: String
)
