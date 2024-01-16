package com.example.sample.application.port.`in`

import com.example.sample.adapter.`in`.web.dto.LoginRequestDTO
import jakarta.servlet.http.HttpSession

interface LogInUseCase {
    fun login(httpSession: HttpSession, loginRequestDTO: LoginRequestDTO): Result?

    data class Result(val email: String, val success: Boolean)
}