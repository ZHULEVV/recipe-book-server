package com.recipebook.presentation.dto

import com.recipebook.domain.entity.Ingredient
import com.recipebook.domain.entity.User
import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(
    val id: String,
    val email: String,
    val displayName: String?,
    val isSubscriber: Boolean,
    val createdAt: String
)

@Serializable
data class UpdateUserRequest(val displayName: String?)

@Serializable
data class IngredientResponse(
    val id: String,
    val name: String,
    val defaultUnit: String
)

fun User.toResponse() = UserResponse(
    id = id.toString(),
    email = email,
    displayName = displayName,
    isSubscriber = isSubscriber,
    createdAt = createdAt.toString()
)

fun Ingredient.toResponse() = IngredientResponse(
    id = id.toString(),
    name = name,
    defaultUnit = defaultUnit
)
