package com.example.ujian_mob_prog.model

data class Geo(
    val lat: String,
    val lng: String
)

data class Address(
    val street: String,
    val suite: String,
    val city: String,
    val zipcode: String,
    val geo: Geo
)

data class User(
    val id: Int,
    val name: String,
    val username: String,
    val email: String,
    val address: Address
)
