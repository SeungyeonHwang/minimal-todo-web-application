package com.example.todo.config

import com.example.todo.database.TodoDataBase
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

// 스프링 부트 실행시, 해당 값들을 먼저 참조 -> Config Class
@Configuration
class AppConfig {

/*  val database = TodoDataBase()와 같은 형태로 static 걸어서 쓰지만
    Spring의 패러다임은 자동으로 주입되게 만드는 것임.*/

    @Bean(initMethod = "init")   // Bean으로 등록, init지정, 빈이 만들어질때 어떤 메소드 참조할지 정한다
    fun todoDataBase(): TodoDataBase {
        return TodoDataBase()
    }

}