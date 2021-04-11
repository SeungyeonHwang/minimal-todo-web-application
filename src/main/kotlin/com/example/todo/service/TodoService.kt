package com.example.todo.service

import com.example.todo.database.Todo
import com.example.todo.database.convertTodo
import com.example.todo.model.http.TodoDto
import com.example.todo.model.http.convertTodoDto
import com.example.todo.repository.TodoRepositoryImpl
import org.springframework.stereotype.Service

/*
* 현업에서 이렇게 변환할 때 사용하는 라이브러
* model mapper
* kotlin reflection
*/
@Service
class TodoService(
    val todoRepositoryImpl: TodoRepositoryImpl
) {

    // C
    fun create(todoDto: TodoDto): TodoDto? {   // Todo는 DB스키마이기 때문에, dto형태로 내려주는게 좋은 코드다
        return todoDto.let {
            Todo().convertTodo(it)
        }.let {
            todoRepositoryImpl.save(it)
        }?.let {    // ?는 무조건 있기 때문에
            TodoDto().convertTodoDto(it)
        }
    }

    // R
    fun read(index: Int): TodoDto? {
        return todoRepositoryImpl.findOne(index)?.let {
            TodoDto().convertTodoDto(it)
        }
    }

    fun readAll(): MutableList<TodoDto> {
        return todoRepositoryImpl.findAll().map {
            TodoDto().convertTodoDto(it)
        }.toMutableList()

    }

    // U
    fun update(todoDto: TodoDto): TodoDto? {
        return todoDto.let {
            Todo().convertTodo(it)
        }.let {
            todoRepositoryImpl.save(it)
        }?.let {    // ?는 무조건 있기 때문에
            TodoDto().convertTodoDto(it)
        }
    }

    // D
    fun delete(index: Int): Boolean {
        return todoRepositoryImpl.delete(index)
    }
}