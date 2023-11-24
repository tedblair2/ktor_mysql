package com.example.db

import com.example.model.User
import com.example.model.Users
import com.example.plugins.dbQuery
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

class UserServiceImpl : UserService {

    private fun resultRowToUser(row: ResultRow):User{
        return User(
            id = row[Users.id],
            name = row[Users.name],
            cityId = row[Users.cityId]
        )
    }

    override suspend fun addUser(user: User): User? = dbQuery{
        val insertStmt=Users.insert {
            it[name]=user.name
            it[cityId]=user.cityId
        }
        insertStmt.resultedValues?.singleOrNull()?.let { resultRowToUser(it) }
    }

    override suspend fun updateUser(user: User): Boolean = dbQuery{
        Users.update({Users.id eq user.id}){
            it[name]=user.name
        }>0
    }

    override suspend fun deleteUser(user: User): Boolean = dbQuery{
        Users.deleteWhere { name eq user.name }>0
    }

    override suspend fun getUsers(): List<User> = dbQuery{
        Users.selectAll().map { resultRowToUser(it) }
    }

    override suspend fun searchUser(query: String): List<User> = dbQuery{
        Users.select { (Users.name.lowerCase() like "%${query.lowercase()}%")}
            .map { resultRowToUser(it) }
    }

    override suspend fun getUser(id: Int): User? = dbQuery{
        Users.select { (Users.id eq id) }.map { resultRowToUser(it) }.singleOrNull()
    }
}