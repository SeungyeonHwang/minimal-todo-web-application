package com.example.todo.controller.api.todo

import com.example.todo.model.http.TodoDto
import com.example.todo.service.TodoService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@Api(description = "일정관리")
@RestController
@RequestMapping("/api/todo")
class TodoApiController(

    //주입 받는다
    val todoService: TodoService
) {

    // 없으면 전체조회, 있으면 단건 조회, required -> 필수값 아닌지 정하기, Optional이므로 Valid할 필요 없다.
    // Read(One)
    @ApiOperation(value = "일정확인", notes = "일정확인 Get API")
    @GetMapping(path = [""])
    fun read(
        @ApiParam(name = "index")
        @RequestParam(required = false) index: Int?
    ): ResponseEntity<Any?> {
        // Index 있을 경우
        return index?.let {
            todoService.read(it)
        }?.let {
            return ResponseEntity.ok(it)
        }   // Index 없는 경우
            ?: kotlin.run {
                return ResponseEntity
                    .status(HttpStatus.MOVED_PERMANENTLY)
                    .header(HttpHeaders.LOCATION, "/api/todo/all")
                    .build()    // 301에러 일으켜서 readAll로 보낸다
            }
    }

    // ReadAll(List)
    @GetMapping(path = ["/all"])
    fun readAll(): MutableList<TodoDto> {
        return todoService.readAll()
    }

    // C
    @PostMapping(path = [""])
    fun create(@RequestBody todoDto: TodoDto): ResponseEntity<Any> {  // Body설계 -> Model , 들어오는 동시에 검증(Valid)
        return ResponseEntity
            .status(200)
            .body(todoService.create(todoDto))
    }

    // U
    @PutMapping(path = [""])    // create = 201, update = 200
    fun update(@Valid @RequestBody todoDto: TodoDto): ResponseEntity<Any> {
        return ResponseEntity
            .status(201)
            .body(todoService.update(todoDto))
    }

    // D
    @DeleteMapping(path = ["/{index}"])
    fun delete(@PathVariable(name = "index") _index: Int): ResponseEntity<Any> {

        if (!todoService.delete(_index)) {
            return ResponseEntity.status(500).build()
        }
        return ResponseEntity.ok().build()
    }
}