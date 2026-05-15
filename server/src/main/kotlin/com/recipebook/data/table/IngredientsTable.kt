package com.recipebook.data.table

import org.jetbrains.exposed.sql.Table

object IngredientsTable : Table("ingredients") {
    val id = uuid("id")
    val name = varchar("name", 255)
    val defaultUnit = varchar("default_unit", 50)

    override val primaryKey = PrimaryKey(id)
}
