package com.example.model

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.Table

@Serializable
data class User(
    val name:String,
    val cityId:Int,
    val id:Int=0
)

object Users:Table(){
    val id=integer("id").autoIncrement()
    val name=varchar("name",255)
    val cityId=integer("city_id").references(Cities.id,ReferenceOption.CASCADE)

    override val primaryKey: PrimaryKey
        get() = PrimaryKey(id)
}
