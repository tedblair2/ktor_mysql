package com.example.db

import com.example.model.Cities
import com.example.model.City
import com.example.model.UserInfo
import com.example.model.Users
import com.example.plugins.dbQuery
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll

class CityServiceImpl : CityService {

    private fun resultRowToCity(resultRow: ResultRow):City{
        return City(
            cityName = resultRow[Cities.cityName],
            id = resultRow[Cities.id]
        )
    }

    override suspend fun addCity(city: City): City? = dbQuery {
        val insertStmt=Cities.insert {
            it[cityName]=city.cityName
        }
        insertStmt.resultedValues?.singleOrNull()?.let { resultRowToCity(it) }
    }

    override suspend fun getAllCities(): List<City> = dbQuery{
        Cities.selectAll().map { resultRowToCity(it) }
    }

    override suspend fun deleteCity(id: Int): Boolean= dbQuery {
        Cities.deleteWhere { Cities.id eq id }>0
    }

    override suspend fun getAllUsersInfo(): List<UserInfo> = dbQuery{
        (Users innerJoin Cities)
            .slice(Users.name,Cities.cityName)
            .selectAll()
            .map {
                UserInfo(
                    name = it[Users.name],
                    city = it[Cities.cityName]
                )
            }
    }
}