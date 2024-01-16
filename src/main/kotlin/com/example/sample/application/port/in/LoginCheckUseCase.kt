package com.example.sample.application.port.`in`

interface LoginCheckUseCase {
    fun loginCheck(loginVO: Any?): Result?

    data class Result(val email: String, val success: Boolean)
}