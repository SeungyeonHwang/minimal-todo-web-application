package com.example.todo.model.http

import java.lang.Exception
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.validation.constraints.AssertTrue
import javax.validation.constraints.NotBlank

// CRUD 하는데 필요한 Body 설계
data class TodoDto(

    var index: Int? = null,

    @field:NotBlank
    var title: String? = null,

    var description: String? = null,

    @field:NotBlank
    // yyyy-MM-dd HH:mm:ss
    var schedule: String? = null,

    var createdAt: LocalDateTime? = null,

    var updatedAt: LocalDateTime? = null

) {
    // TODO 이전 학습한 custom annotation으로 변경 -> Test필요
    @AssertTrue(message = "yyyy-MM-dd HH:mm:ss 포맷이 맞지 않습니다.")
    fun validSchedule(): Boolean {
        return try {
            LocalDateTime.parse(
                schedule,
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
            )   // 파싱해서 성공 -> true, 실패 -> false
            true
        } catch (e: Exception) {
            false
        }
    }
}