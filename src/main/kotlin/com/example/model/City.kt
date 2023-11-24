package com.example.model

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Table

@Serializable
data class City(
    val cityName:String,
    val id:Int=0
)

object Cities:Table(){
    val id=integer("id").autoIncrement()
    val cityName=varchar("city_name",255)

    override val primaryKey: PrimaryKey
        get() = PrimaryKey(id)
}
