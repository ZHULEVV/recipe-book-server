package com.recipebook.data.table

import org.jetbrains.exposed.sql.Table

object TagsTable : Table("tags") {
    val id = uuid("id")
    val name = varchar("name", 100)
    val category = varchar("category", 20)

    override val primaryKey = PrimaryKey(id)
}
