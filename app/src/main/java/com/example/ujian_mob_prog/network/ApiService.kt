package com.example.ujian_mob_prog.network

import com.example.ujian_mob_prog.model.User
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("/users")
    fun getUsers(): Call<List<User>>
}
