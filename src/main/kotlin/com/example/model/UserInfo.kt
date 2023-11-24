package com.example.model

import kotlinx.serialization.Serializable

@Serializable
data class UserInfo(
    val name:String,
    val city:String
)
