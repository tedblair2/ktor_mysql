package com.example.db

import com.example.model.City
import com.example.model.UserInfo

interface CityService {
    suspend fun addCity(city: City):City?
    suspend fun getAllCities():List<City>
    suspend fun deleteCity(id:Int):Boolean
    suspend fun getAllUsersInfo():List<UserInfo>
}