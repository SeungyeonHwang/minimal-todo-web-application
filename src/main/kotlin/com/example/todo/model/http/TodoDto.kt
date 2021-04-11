package com.example.todo.model.http

import com.example.todo.database.Todo
import io.swagger.annotations.ApiModelProperty
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.validation.constraints.AssertTrue
import javax.validation.constraints.NotBlank

// CRUD 하는데 필요한 Body 설계
data class TodoDto(

    @field:ApiModelProperty(
        value = "DB INDEX",
        example = "1",
        required = false
    )
    var index: Int? = null,

    @field:ApiModelProperty(
        value = "일정명",
        example = "일정관리",
        required = true
    )
    @field:NotBlank
    var title: String? = null,

    @field:ApiModelProperty(
        value = "일정설명",
        example = "13시 스타벅스",
        required = false
    )
    var description: String? = null,

    @field:ApiModelProperty(
        value = "시간",
        example = "2020-01-01 00:00:00",
        required = true
    )
    @field:NotBlank
    // yyyy-MM-dd HH:mm:ss
    var schedule: String? = null,

    @field:ApiModelProperty(
        value = "생성시간",
        required = false
    )
    var createdAt: LocalDateTime? = null,

    @field:ApiModelProperty(
        value = "수정시간",
        required = false
    )
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

fun TodoDto.convertTodoDto(todo: Todo): TodoDto {

    return TodoDto().apply {
        this.index = todo.index
        this.title = todo.title
        this.description = todo.description
        this.schedule = todo.schedule?.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
        this.createdAt = todo.createdAt
        this.updatedAt = todo.updatedAt
    }
}


