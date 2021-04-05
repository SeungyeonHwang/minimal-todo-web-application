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

    override fun save(todo: Todo): Todo {

        return todo.apply {
            this.index = ++todoDataBase.index
            this.createdAt = LocalDateTime.now()
            this.updatedAt = LocalDateTime.now()
        }.run {
            todoDataBase.todoList.add(todo)
            this    // return
        }
1
//        val index = todoDataBase.index++
//        todo.index = index
//        todoDataBase.todoList.add(todo)
    }

    override fun saveAll(todoList: MutableList<Todo>): Boolean {
        TODO("Not yet implemented")
    }

    override fun update(todo: Todo): Todo {
        TODO("Not yet implemented")
    }

    override fun delete(index: Int): Boolean {
        TODO("Not yet implemented")
    }

    override fun findOne(index: Int): Todo {
        TODO("Not yet implemented")
    }

    override fun findAll(): MutableList<Todo> {
        TODO("Not yet implemented")
    }
}