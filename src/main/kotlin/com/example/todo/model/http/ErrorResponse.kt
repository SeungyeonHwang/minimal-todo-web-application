package com.example.todo.model.http

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDateTime

/*
{
    "result_code" : "OK",

    "http_status" : "400",
    "http_method" : "",
    "message" : "요청이 잘못 되었습니다.",
    "path" : "/api/exception/hello",
    "timestamp" : "2020-10-02T13:00:00",
    "error" : [
        {
            "field" : "_name",
            "message" : "5글자 이상이여야 합니다.",
            "value" : ""
        }
    ]
}
*/
data class ErrorResponse(

    @JsonProperty("reseult_code")   // -> snake_case 로 변경
    var resultCode: String? = null,

    @JsonProperty("http_status")
    var httpStatus: String? = null,

    @JsonProperty("http_method")
    var httpMethod: String? = null,

    var message: String? = null,

    var path: String? = null,

    var timestamp: LocalDateTime? = null,

    var error: MutableList<Error>? = null

)

data class Error(
    var field: String? = null,
    var message: String? = null,
    var value: Any? = null
)