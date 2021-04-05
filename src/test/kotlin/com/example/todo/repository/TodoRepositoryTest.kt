package com.example.todo.repository

import com.example.todo.config.AppConfig
import com.example.todo.database.Todo
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalDateTime

//TDD : Test만들면서 최소한의 코드를 코딩해 나간다

@SpringBootTest(classes = [TodoRepositoryImpl::class, AppConfig::class])  // 특정 클래스만 테스트(import), 효율적 테스트 위해, Bean도 불러와 준다
class TodoRepositoryTest {

    @Autowired
    lateinit var todoRepositoryImpl: TodoRepositoryImpl

    @Test
    fun saveTest() {
        val todo = Todo().apply {
            this.title = "테스트 일정"
            this.description = "테스트"
            this.schedule = LocalDateTime.now()
        }

        val result = todoRepositoryImpl.save(todo)

        // 자동으로 생성해주는 데이터베이스 값 Test -> Pass
        Assertions.assertEquals(1, result.index)
        Assertions.assertNotNull(result.createdAt)
        Assertions.assertNotNull(result.updatedAt)
        Assertions.assertEquals("테스트 일정", result.title)
        Assertions.assertEquals("테스트", result.description)
    }

}