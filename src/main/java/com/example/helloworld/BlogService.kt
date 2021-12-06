package com.example.helloworld

import coroutines.ToDo
import coroutines.User
import retrofit2.http.GET
import retrofit2.http.Path

interface BlogService {
    @GET("/todos/{id}")
    suspend fun getToDo(@Path("id") todoId: Int): ToDo

    @GET("/users/{id}")
    suspend fun getUser(@Path("id") userId: Int): User

    @GET("/users/{id}/posts")
    suspend fun getToDosByUser(@Path("id") userId: Int): List<ToDo>
}