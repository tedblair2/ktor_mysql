package com.example.di

import com.example.db.CityService
import com.example.db.CityServiceImpl
import com.example.db.UserService
import com.example.db.UserServiceImpl
import org.flywaydb.core.Flyway
import org.koin.dsl.module

val appModule= module {
    single<UserService> {
        UserServiceImpl()
    }
    single<CityService> {
        CityServiceImpl()
    }
}