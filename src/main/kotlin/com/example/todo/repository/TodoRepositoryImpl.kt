package com.example.todo.repository

import com.example.todo.database.Todo
import com.example.todo.database.TodoDataBase
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDateTime

// TodoRepository 상속
@Service
class TodoRepositoryImpl : TodoRepository {

    @Autowired  // database init(Config)가 자동으로 주입됨, Autowired로 언제든지 접근 가능
    lateinit var todoDataBase: TodoDataBase

    override fun save(todo: Todo): Todo? {

        // 1. index?
        return todo.index?.let { index ->
            // update(값이 있을 때)
            findOne(index)?.apply {
                this.title = todo.title
                this.description = todo.description
                this.schedule = todo.schedule
                this.updatedAt = LocalDateTime.now()
            }
        } ?: kotlin.run {
            // insert(값이 없을 때)
            todo.apply {
                this.index = ++todoDataBase.index
                this.createdAt = LocalDateTime.now()
                this.updatedAt = LocalDateTime.now()
            }.run {
                todoDataBase.todoList.add(todo)
                this    // return
            }
        }
    }

    override fun saveAll(todoList: MutableList<Todo>): Boolean {
        return try {
            todoList.forEach {
                save(it)
            }
            true
        } catch (e: Exception) {
            false
        }
    }

//    override fun update(todo: Todo): Todo {
//        // jpa
//        // save db index -> 인덱스 있냐 업냐, 있으면 -> 업데이트, 없으면 -> insert
//    }

    override fun delete(index: Int): Boolean {

        return findOne(index)?.let {
            todoDataBase.todoList.remove(it)
            true    // 값이 있을 때
        } ?: kotlin.run {
            false   // 값이 없을 때
        }
    }

    override fun findOne(index: Int): Todo? {
        return todoDataBase.todoList.first { it.index == index }
    }

    override fun findAll(): MutableList<Todo> {
        return todoDataBase.todoList
    }
}