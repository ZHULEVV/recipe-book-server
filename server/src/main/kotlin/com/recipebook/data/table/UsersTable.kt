package com.recipebook.data.table

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.kotlin.datetime.timestamp

object UsersTable : Table("users") {
    val id = uuid("id")
    val firebaseUid = varchar("firebase_uid", 128)
    val email = varchar("email", 255)
    val displayName = varchar("display_name", 255).nullable()
    val isSubscriber = bool("is_subscriber")
    val createdAt = timestamp("created_at")

    override val primaryKey = PrimaryKey(id)
}
