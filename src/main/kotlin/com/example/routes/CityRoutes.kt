package com.example.routes

import com.example.db.CityService
import com.example.model.City
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Routing.cityRoute(cityService: CityService){
    route("/cities"){
        get {
            val cities=cityService.getAllCities()
            call.respond(HttpStatusCode.OK,cities)
        }
        post {
            val city=call.receive<City>()
            cityService.addCity(city)?.let {
                call.respond(HttpStatusCode.Created,it)
            } ?: call.respond(HttpStatusCode.BadRequest,"Error!!")
        }
        get("/userinfo"){
            val userInfo=cityService.getAllUsersInfo()
            call.respond(HttpStatusCode.OK,userInfo)
        }
        delete("/{id}") {
            call.parameters["id"]?.toInt()?.let {
                cityService.deleteCity(it)
            } ?: call.respond(HttpStatusCode.BadRequest,"Provide Id!!")
        }
    }
}