package com.example.todo.controller.api.todo

import com.example.todo.model.http.TodoDto
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api/todo")
class TodoApiController {

    // R
    @GetMapping(path = [""])
    fun read(@RequestParam(required = false) index: Int?) { // 없으면 전체조회, 있으면 단건 조회, required -> 필수값 아닌지 정하기, Optional이므로 Valid할 필요 없다.

    }

    // C
    @PostMapping(path = [""])
    fun create(@Valid @RequestBody todoDto: TodoDto) {  // Body설계 -> Model , 들어오는 동시에 검증(Valid)

    }

    // U
    @PutMapping(path = [""])
    fun update(@Valid @RequestBody todoDto: TodoDto) {

    }

    // D
    @DeleteMapping(path = ["/{index}"])
    fun delete(@PathVariable(name = "index") _index: Int) {

    }

}