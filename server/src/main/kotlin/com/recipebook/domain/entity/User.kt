package com.recipebook.domain.entity

import java.time.Instant
import java.util.UUID

data class User(
    val id: UUID,
    val firebaseUid: String,
    val email: String,
    val displayName: String?,
    val isSubscriber: Boolean,
    val createdAt: Instant
)
