package com.example.plugins

import com.example.db.CityService
import com.example.db.UserService
import com.example.routes.cityRoute
import com.example.routes.userRoute
import io.ktor.server.application.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.get

fun Application.configureRouting(userService: UserService=get(),cityService: CityService=get()) {
    routing {
        userRoute(userService)
        cityRoute(cityService)
    }
}
