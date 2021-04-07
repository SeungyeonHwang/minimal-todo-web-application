package com.example.todo.model.http

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import javax.validation.Validation

class TodoDtoTest {

    val validator = Validation.buildDefaultValidatorFactory().validator

    @Test
    fun todoDtoTest() {

        val todoDto = TodoDto().apply {
            this.title = "테스트"
            this.description = ""
            this.schedule = "2020-10-20 13:00:00"
        }

        val result = validator.validate(todoDto)

//        # 출력해서 확인
//        result.forEach {
//            println(it.propertyPath.last().name)
//            println(it.message)
//            println(it.invalidValue)
//        }

        // 에러 없는 경우 True, 있는경우 False
        Assertions.assertEquals(true, result.isEmpty())
    }
}