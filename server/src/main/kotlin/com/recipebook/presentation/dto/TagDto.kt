package com.recipebook.presentation.dto

import com.recipebook.domain.entity.Tag
import kotlinx.serialization.Serializable

@Serializable
data class TagResponse(val id: String, val name: String, val category: String)

fun Tag.toResponse() = TagResponse(id = id.toString(), name = name, category = category.name)
