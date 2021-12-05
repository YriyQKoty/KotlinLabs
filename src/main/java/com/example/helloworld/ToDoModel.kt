package com.example.helloworld

class ToDoModel {
    companion object Factory {
        fun createList() : ToDoModel = ToDoModel()
    }

    var Uid: String? = null
    var itemDataText: String? = ""
    var done: Boolean? = false
}