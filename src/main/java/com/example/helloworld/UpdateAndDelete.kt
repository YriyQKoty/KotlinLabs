package com.example.helloworld

interface  UpdateAndDelete {
    fun modifyItem(itemUid: String, isDone: Boolean)
    fun onItemDelete(itemUid: String)
}