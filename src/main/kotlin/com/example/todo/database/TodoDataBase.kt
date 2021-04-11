package com.example.todo.database

//Index
data class TodoDataBase(
    var index: Int = 0,
    var todoList: MutableList<Todo> = mutableListOf()
) {
    fun init() {
        this.index = 0
        this.todoList = mutableListOf() //초기화
        println("[DEBUG] todo database init")
    }
}