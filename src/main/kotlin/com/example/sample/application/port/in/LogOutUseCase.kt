package com.example.sample.application.port.`in`

import jakarta.servlet.http.HttpSession

interface LogOutUseCase {
    fun logout(httpSession: HttpSession): Result

    data class Result(val success: Boolean)
}