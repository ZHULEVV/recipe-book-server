package com.recipebook.presentation.dto

import com.recipebook.domain.entity.RecipeRating
import kotlinx.serialization.Serializable

@Serializable
data class RatingResponse(val averageScore: Double?, val count: Long)

@Serializable
data class RateRequest(val score: Int)

fun RecipeRating.toResponse() = RatingResponse(averageScore = averageScore, count = count)
